package items.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

/**
 * Created by CH on 05/07/2015.
 */
public class SoundField implements AbstractField {

    private String fieldName;
    private String[] values;

    private Container panel;
    private JComboBox fieldInstance;
    private JLabel labelInstance;
    private BasicField pitch;
    private String valueName;

    private int xPos;
    private int yPos;
    private int xLength = 150;

    private static final int yLength = 18;

    private static final String[] sounds = new String[]{
            "null",
            "Ambience Cave",
            "Ambience Rain",
            "Ambience Thunder",
            "Anvil Break",
            "Anvil Land",
            "Anvil Use",
            "Arrow Hit",
            "Bat Death",
            "Bat Hurt",
            "Bat Idle",
            "Bat Loop",
            "Bat Takeoff",
            "Blaze Breath",
            "Blaze Death",
            "Blaze Hit",
            "Breath",
            "Burp",
            "Cat Hiss",
            "Cat Hit",
            "Cat Meow",
            "Cat Purr",
            "Cat Purreow",
            "Chest Close",
            "Chest Open",
            "Chicken Egg Pop",
            "Chicken Hurt",
            "Chicken Idle",
            "Chicken Walk",
            "Click",
            "Cow Hurt",
            "Cow Idle",
            "Cow Walk",
            "Creeper Death",
            "Creeper Hiss",
            "Dig Grass",
            "Dig Gravel",
            "Dig Sand",
            "Dig Snow",
            "Dig Stone",
            "Dig Wood",
            "Dig Wool",
            "Donkey Angry",
            "Donkey Death",
            "Donkey Hit",
            "Donkey Idle",
            "Door Close",
            "Door Open",
            "Drink",
            "Eat",
            "Enderdragon Death",
            "Enderdragon Growl",
            "Enderdragon Hit",
            "Enderdragon Wings",
            "Enderman Death",
            "Enderman Hit",
            "Enderman Idle",
            "Enderman Scream",
            "Enderman Stare",
            "Enderman Teleport",
            "Explode",
            "Fall Big",
            "Fall Small",
            "Fire",
            "Firework Blast",
            "Firework Blast2",
            "Firework Large Blast",
            "Firework Large Blast2",
            "Firework Launch",
            "Firework Twinkle",
            "Firework Twinkle2",
            "Fire Ignite",
            "Fizz",
            "Fuse",
            "Ghast Charge",
            "Ghast Death",
            "Ghast Fireball",
            "Ghast Moan",
            "Ghast Scream",
            "Ghast Scream2",
            "Glass",
            "Horse Angry",
            "Horse Armor",
            "Horse Breathe",
            "Horse Gallop",
            "Horse Hit",
            "Horse Idle",
            "Horse Jump",
            "Horse Land",
            "Horse Saddle",
            "Horse Skeleton Death",
            "Horse Skeleton Idle",
            "Horse Soft",
            "Horse Wood",
            "Horse Zombie Death",
            "Horse Zombie Hit",
            "Horse Zombie Idle",
            "Hurt",
            "Hurt Flesh",
            "Irongolem Death",
            "Irongolem Hit",
            "Irongolem Throw",
            "Irongolem Walk",
            "Item Break",
            "Item Pickup",
            "Lava",
            "Lava Pop",
            "Level Up",
            "Magmacube Jump",
            "Magmacube Walk",
            "Magmacube Walk2",
            "Minecart Base",
            "Minecart Inside",
            "Note Bass",
            "Note Bass Drum",
            "Note Bass Guitar",
            "Note Piano",
            "Note Pling",
            "Note Snare Drum",
            "Note Sticks",
            "Orb Pickup",
            "Pig Death",
            "Pig Idle",
            "Pig Walk",
            "Piston Extend",
            "Piston Retract",
            "Portal",
            "Portal Travel",
            "Portal Trigger",
            "Sheep Idle",
            "Sheep Shear",
            "Sheep Walk",
            "Shoot Arrow",
            "Silverfish Hit",
            "Silverfish Idle",
            "Silverfish Kill",
            "Silverfish Walk",
            "Skeleton Death",
            "Skeleton Hurt",
            "Skeleton Idle",
            "Skeleton Walk",
            "Slime Attack",
            "Slime Walk",
            "Slime Walk2",
            "Spider Death",
            "Spider Idle",
            "Spider Walk",
            "Splash",
            "Splash2",
            "Step Grass",
            "Step Gravel",
            "Step Ladder",
            "Step Sand",
            "Step Snow",
            "Step Stone",
            "Step Wood",
            "Step Wool",
            "Successful Hit",
            "Swim",
            "Villager Death",
            "Villager Haggle",
            "Villager Hit",
            "Villager Idle",
            "Villager No",
            "Villager Yes",
            "Water",
            "Wither Death",
            "Wither Hurt",
            "Wither Idle",
            "Wither Shoot",
            "Wither Spawn",
            "Wolf Bark",
            "Wolf Death",
            "Wolf Growl",
            "Wolf Howl",
            "Wolf Hurt",
            "Wolf Pant",
            "Wolf Shake",
            "Wolf Walk",
            "Wolf Whine",
            "Wood Click",
            "Zombie Death",
            "Zombie Hurt",
            "Zombie Idle",
            "Zombie Infect",
            "Zombie Metal",
            "Zombie Pig Angry",
            "Zombie Pig Death",
            "Zombie Pig Hurt",
            "Zombie Pig Idle",
            "Zombie Remedy",
            "Zombie Unfect",
            "Zombie Wood",
            "Zombie Woodbreak"
    };

    public SoundField(String name, String valueName, Container panel){
        fieldName = name;
        this.panel = panel;
        this.valueName = valueName;
        values = sounds;
    }

    public void setPosition(int x , int y){
        xPos = x;
        yPos = y;
    }

    public void setLength(int length){
        xLength = length;
    }

    @SuppressWarnings("unchecked")
    public void draw(BasicField basic){
        JComboBox field = new JComboBox(values);
        field.setSelectedIndex(0);
        JLabel label = new JLabel(fieldName + ":");
        int width = (int) label.getPreferredSize().getWidth();
        label.setBounds(xPos,yPos,width,yLength);
        labelInstance = (JLabel) panel.add(label);
        field.setBounds(xPos + width + 5, yPos, xLength, yLength);
        fieldInstance = (JComboBox) panel.add(field);
        pitch = basic;

    }


    public String getRawText(){
        return (String) fieldInstance.getSelectedItem();
    }

    public FieldConverter getConverter(){
        return new FieldConverter(valueName,new BasicDataConverter(){
            @Override
            public String getData(AbstractField field){
                return "'" + field.getRawText() + ":" + pitch.getRawText() + "'";
            }
        });

    }

    public void setKeyListener(KeyListener listener){

    }
    public void addFocusListener(FocusListener listener){

    }
}
