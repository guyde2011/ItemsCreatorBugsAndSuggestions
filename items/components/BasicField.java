package items.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

/**
 * Created by CH on 05/07/2015.
 */
public class BasicField implements AbstractField {

    private String fieldName;
    private String defaultValue = "";

    private Container panel;
    private JTextField fieldInstance;
    private JLabel labelInstance;
    private FieldConverter converter;
    private KeyListener listener;
    private FocusListener listener2;
    private int xPos;
    private int yPos;
    private int xLength = 200;

    private static final int yLength = 18;

    public BasicField(String name, Container panel, FieldConverter converter){
        fieldName = name;
        this.panel = panel;
        this.converter = converter;
        converter.getConverter().applyData(this);
    }

    public void setDefault(String def){
        defaultValue = def;
    }

    public void setPosition(int x , int y){
        xPos = x;
        yPos = y;
    }

    public void setLength(int length){
        xLength = length;
    }

    public void draw(){
        JTextField field = new JTextField(defaultValue);
        JLabel label = new JLabel(fieldName + ":");
        int width = (int) label.getPreferredSize().getWidth();
        label.setBounds(xPos,yPos,width,yLength);
        labelInstance = (JLabel) panel.add(label);
        if (listener!=null){
            field.addKeyListener(listener);
        }
        if (listener2!=null){
            field.addFocusListener(listener2);
        }
        field.setBounds(xPos + width + 5, yPos, xLength, yLength);
        fieldInstance = (JTextField) panel.add(field);
    }

    public String getRawText(){
        return fieldInstance.getText();
    }

    public FieldConverter getConverter(){
        return converter;
    }

    public void setKeyListener(KeyListener listener){
        this.listener = listener;
    }

    public void addFocusListener(FocusListener listener){
        this.listener2 = listener;
    }

}
