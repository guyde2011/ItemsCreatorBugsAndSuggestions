package items.components;


import items.mobs.Item;
import items.mobs.ItemTier;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CH on 05/07/2015.
 */
public class OrderedPanel implements Submitable{

    private static final int xPos = 10;
    private static final int yPos = 5;

    private List<AbstractField> list = new LinkedList<AbstractField>();
    public JFrame frame;
    public int count = 0;
    private int countExtra = 9 ;
    private JTextArea display;
    JPanel fieldsPanel;
    private JLabel message;

    private static final String[] items = new String[]{
        "Spear","Bow","Dagger","Wand","Boots","Leggings","Helmet","Chestplate"
    };

    public OrderedPanel(){
        init();
    }

    public void onSubmit(){
        StringBuilder builder = new StringBuilder();
         boolean b = false;
        for (AbstractField field : list){
            if (field.getConverter().shouldCheck(field)) {
                if (!b){
                    b = true;
                } else {
                    builder.append("\n");
                }
                builder.append(field.getConverter().getName());
                builder.append(": ");
                builder.append(field.getConverter().getYMLData(field));
            }
        }
        display.setText(builder.toString());
    }

    private void init(){
        frame = new JFrame("Items YML Creator");
        Color bg = new Color(225,235,255);
        frame.pack();
        frame.setSize(900, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
        frame.getContentPane().setBackground(bg);
        frame.getContentPane().setForeground(bg);
        fieldsPanel = new JPanel();
        fieldsPanel.setPreferredSize(new Dimension(880,1450));
        fieldsPanel.setLayout(null);
        fieldsPanel.setBackground(bg);
        fieldsPanel.setForeground(bg);
        JScrollPane scrollPanel = new JScrollPane(fieldsPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setBorder(new TitledBorder(new EtchedBorder(), "Fields"));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(16);
        scrollPanel.setBounds(10, 15, 880, 580);
        scrollPanel.setBackground(bg);
        scrollPanel.setForeground(bg);
        frame.add(scrollPanel);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "YML"));
        display = new JTextArea(4,70);
        panel.setBackground(bg);
        panel.setForeground(bg);
        final JLabel label1 = new JLabel();
        label1.setBounds(170,24,880,18);
        TexturedButton button = new TexturedButton("copyall.png") {
                @Override
                public void onClick() {
                    StringSelection selection = new StringSelection(display.getText());
                    Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
                    cb.setContents(selection, null);
                    label1.setText("Successfully copied.");
                }
            };
        message = label1;
        button.setBounds(30,13,button.getWidth(),button.getHeight());
        panel.add(button);
        panel.add(label1);
        display.setEditable(false);
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        display.setBounds(20,50,800,80);
        scroll.setBounds(20, 50, 800, 80);
        panel.add(scroll);
        panel.setBounds(20, 600, 860, 160);
        frame.add(panel);
        postInit();
    }

    private void postInit(){
        addField("Item Name", "name", "null", Converters.RAW);
        addSelectionField("Item Type", "type", items, Converters.RAW);
        final SelectionField field1 = (SelectionField)list.get(list.size()-1);
        addSelectionField("Item Tier", "tier", new String[]{"Normal", "Unique", "Rare", "Legendary"}, Converters.RAW);
        final SelectionField field2 = (SelectionField)list.get(list.size()-1);
        addField("Item Required Level", "level", "1", Converters.INT);
        final BasicField field3 = (BasicField)list.get(list.size()-1);
        addField("Item Lore (For Legendary Weapons)", "addedLore", "null", Converters.RAW);
        final BasicField field4 = (BasicField)list.get(list.size()-1);
        TexturedButton button = new TexturedButton("generate.png") {
            @Override
            public void onClick() {
                if (list.size()>4) {
                    reset();
                }
                    List<String> l = Arrays.asList("Wand","Spear","Dagger","Bow","Boots","Leggings","Helmet","Chestplate");
                    ItemTier tier = ItemTier.valueOf(field2.getRawText().toUpperCase());
                    int level = Integer.parseInt(field3.getRawText());
                    String itemLore = field4.getRawText();
                    Item item = new Item(field1.getRawText());
                    int k = l.indexOf(field1.getRawText());
                    item.createItems(OrderedPanel.this,level,tier,itemLore, k<4 ? 1d + (k-2) * 0.2d : 1);
                    frame.repaint();

            }
        };
        TexturedButton button1 = new TexturedButton("submit.png") {
            @Override
            public void onClick() {
                OrderedPanel.this.onSubmit();
                message.setText("");
            }
        };
        count++;
        button.setBounds(xPos, count * 24, button.getWidth(),button.getHeight());
        button1.setBounds(xPos + 400, count * 24, button1.getWidth(),button1.getHeight());
        count+=2;
        fieldsPanel.add(button);
        fieldsPanel.add(button1);
    }

    public void draw(){
        frame.setVisible(true);
    }

    public void reset(){
        int c = fieldsPanel.getComponentCount();
        for (int i = 0; i<c-12; i++){
            fieldsPanel.remove(12);
        }
        count = 9;
        countExtra = 9;
        List<AbstractField> copy = list;
        list = new LinkedList<AbstractField>();
        list.add(copy.get(0));
        list.add(copy.get(1));
        list.add(copy.get(2));
        list.add(copy.get(3));
    }

    public void addSoundField(String name, String valueName){
        SoundField sound_field = new SoundField(name,valueName,fieldsPanel);
        FieldConverter converter = new FieldConverter(valueName, Converters.NUMBER);
        BasicField field = new BasicField(name + " Pitch", fieldsPanel, converter);
        field.setDefault("1.0");
        sound_field.setPosition(xPos, yPos + 24 * count);
        count++;
        field.setPosition(xPos, yPos + 24 * count);
        sound_field.draw(field);
        field.draw();
        list.add(sound_field);
        list.add(field);
        count++;
    }

    public void addFieldAt(String name, String valueName, YMLDataConverter ymlConverter, int x, int y) {
        FieldConverter converter = new FieldConverter(valueName, ymlConverter);
        BasicField field = new BasicField(name, fieldsPanel, converter);
        field.setPosition(xPos + x, yPos + 24 * count + y);
        field.draw();
        list.add(field);
    }

    public void addFieldAt(String name, String valueName, String defaultVal, YMLDataConverter ymlConverter, int x, int y) {
        FieldConverter converter = new FieldConverter(valueName, ymlConverter);
        BasicField field = new BasicField(name, fieldsPanel, converter);
        field.setDefault(defaultVal);
        field.setPosition(xPos + x, yPos + 24 * count + y);
        field.draw();
        list.add(field);
    }

    public void addLabelAt(JLabel label, int x, int y) {
        label.setBounds(x + xPos, yPos + 24 * count + y, label.getWidth(), label.getHeight());
        fieldsPanel.add(label);
    }

    public void addComponentAt(Component label, int x, int y) {
        label.setBounds(x + xPos, yPos + 24 * count + y, label.getWidth(), label.getHeight());
        fieldsPanel.add(label);
    }

    public void addField(String name, String valueName, YMLDataConverter ymlConverter) {
        FieldConverter converter = new FieldConverter(valueName, ymlConverter);
        BasicField field = new BasicField(name, fieldsPanel, converter);
        field.setPosition(xPos, yPos + 24 * count);
        field.draw();
        count++;
        list.add(field);
    }

    public void addField(String name, String valueName, String defaultVal, YMLDataConverter ymlConverter){
        FieldConverter converter = new FieldConverter(valueName ,ymlConverter);
        BasicField field = new BasicField(name,fieldsPanel,converter);
        field.setDefault(defaultVal);
        field.setPosition(xPos, yPos + 24 * count);
        field.draw();
        count++;
        list.add(field);
    }

    public void addSilentField(String name, String valueName, String defaultVal, YMLDataConverter ymlConverter){
        FieldConverter converter = new FieldConverter(valueName ,ymlConverter);
        BasicField field = new BasicField(name,fieldsPanel,converter);
        field.setDefault(defaultVal);
        field.setPosition(xPos, yPos + 24 * count);
        field.draw();
        count++;
    }

    public SelectionField addSilentSelectionField(String name, String valueName, String[] values , YMLDataConverter ymlConverter){
        FieldConverter converter = new FieldConverter(valueName ,ymlConverter);
        SelectionField field = new SelectionField(name,fieldsPanel,converter);
        field.setValues(values);
        field.setPosition(xPos, yPos + 24 * count);
        field.draw();
        count++;
        return field;
    }

    public void addSelectionField(String name, String valueName, String[] values , YMLDataConverter ymlConverter){
        FieldConverter converter = new FieldConverter(valueName ,ymlConverter);
        SelectionField field = new SelectionField(name,fieldsPanel,converter);
        field.setValues(values);
        field.setPosition(xPos, yPos + 24 * count);
        field.draw();
        count++;
        list.add(field);
    }

    public void addExtraField(String name, String valueName, YMLDataConverter ymlConverter){
        FieldConverter converter = new FieldConverter(valueName ,ymlConverter);
        BasicField field = new BasicField(name,fieldsPanel,converter);
        field.setPosition(xPos + 350, yPos + 24 * countExtra);
        field.draw();
        countExtra++;
        list.add(field);
    }

    public void addExtraField(String name, String valueName, String defaultVal, YMLDataConverter ymlConverter){
        FieldConverter converter = new FieldConverter(valueName ,ymlConverter);
        BasicField field = new BasicField(name,fieldsPanel,converter);
        field.setDefault(defaultVal);
        field.setPosition(xPos + 350, yPos + 24 * countExtra);
        field.draw();
        countExtra++;
        list.add(field);
    }

    public void addExtraSelectionField(String name, String valueName, String[] values , YMLDataConverter ymlConverter){
        FieldConverter converter = new FieldConverter(valueName ,ymlConverter);
        SelectionField field = new SelectionField(name,fieldsPanel,converter);
        field.setValues(values);
        field.setPosition(xPos + 350, yPos + 24 * countExtra);
        field.draw();
        countExtra++;
        list.add(field);
    }
}
