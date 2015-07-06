package items.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

/**
 * Created by CH on 05/07/2015.
 */
public class SelectionField implements AbstractField {

    private String fieldName;
    private String[] values;

    private Container panel;
    private JComboBox fieldInstance;
    private JLabel labelInstance;
    private FieldConverter converter;

    private int xPos;
    private int yPos;
    private int xLength = 160;

    private static final int yLength = 18;

    public SelectionField(String name, Container panel, FieldConverter converter){
        fieldName = name;
        this.panel = panel;
        this.converter = converter;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public void setPosition(int x , int y){
        xPos = x;
        yPos = y;
    }

    public void setLength(int length){
        xLength = length;
    }

    @SuppressWarnings("unchecked")
    public void draw(){
        JComboBox field = new JComboBox(values);
        field.setSelectedIndex(0);
        JLabel label = new JLabel(fieldName + ":");
        int width = (int) label.getPreferredSize().getWidth();
        if (this.values.length==2){
            xLength = 80;
        }
        label.setBounds(xPos,yPos,width,yLength);
        labelInstance = (JLabel) panel.add(label);
        field.setBounds(xPos + width + 5, yPos, xLength, yLength);
        fieldInstance = (JComboBox) panel.add(field);
    }

    public String getRawText(){
        return (String) fieldInstance.getSelectedItem();
    }

    public FieldConverter getConverter(){
        return converter;
    }

    public void setKeyListener(KeyListener listener){

    }

    public void addFocusListener(FocusListener listener){

    }
}
