package items.mobs;

import items.components.*;

import javax.swing.*;
import java.util.*;
import java.util.Arrays;
import java.awt.*;
import java.util.List;

/**
 * Created by CH on 05/07/2015.
 */
public class Item {

    private String type;





    public Item(String itemType){
        type = itemType;
    }

    public void createItems(OrderedPanel panel, int level, ItemTier tier, String itemLore, double dmgMulti){
        List<String> weapons = (List<String>) Arrays.asList("Spear","Dagger","Bow","Wand");
        if (weapons.contains(type)){
            createWeaponFields(panel,tier,level,dmgMulti);
        } else {
            createArmorFields(panel,tier,level);
        }



        createExtraFields(panel, level);
    }

    public void createWeaponFields(final OrderedPanel panel, ItemTier tier, int level, double dmgMulti){
        int xOff = 20;
        int xOff1 = 80;
        int xOff2 = 300;
        int xOff3 = 480;
        int a = 60;
        int xOff4 = xOff3 + a;
        int xOff5 = xOff4 + 85;
        int xOff6 = xOff5 + a + 30;
        int i = 1;
        int min = (int) (tier.getMinDMG(level) * dmgMulti);
        int max = (int) (tier.getMaxDMG(level) * dmgMulti);
        panel.addField("Damage","damage", min + "-" + max,Converters.ELEMENT_DMG);
        panel.addSelectionField("Attack Speed","attackSpeed",new String[]{"Very Slow","Slow","Normal","Fast","Very Fast"},Converters.BIG);
        panel.addField("Powder Sockets","sockets","0",Converters.INT);
        final String[] strings = new String[]{"Thunder","Fire","Water","Air","Earth"};
        String[] weak = new String[]{"Earth","Water","Thunder","Fire","Air"};
        String[] strong = new String[]{"Water","Air","Fire","Earth","Thunder"};
        for (String element : strings){
            String unlocal = element.toLowerCase();
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(TexturedButton.class.getResource(unlocal + ".png")));
            label.setSize(new Dimension(32, 32));
            panel.addLabelAt(label, xOff, i * 40 - 8);
            panel.addFieldAt(element + " Damage", unlocal + "Damage", "0-0", Converters.ELEMENT_DMG, xOff1, i * 40);
            Converters.addElement(element,panel);
            JLabel label1 = new JLabel();
            label1.setIcon(new ImageIcon(TexturedButton.class.getResource(weak[i-1].toLowerCase() + ".png")));
            label1.setSize(new Dimension(32,32));
            JLabel label2 = new JLabel("Weak To:");
            int width1 = (int) label2.getPreferredSize().getWidth();
            label2.setSize(new Dimension(width1,18));
            JLabel label3 = new JLabel();
            label3.setIcon(new ImageIcon(TexturedButton.class.getResource(strong[i-1].toLowerCase() + ".png")));
            label3.setSize(new Dimension(32,32));
            JLabel label4 = new JLabel("Strong Against:");
            int width2 = (int) label4.getPreferredSize().getWidth();
            label4.setSize(new Dimension(width2,18));
            panel.addLabelAt(label2, xOff2,i * 40);
            panel.addLabelAt(label1, xOff2 + 60,i * 40 - 8);
            panel.addLabelAt(label4, xOff2 + 100,i * 40);
            panel.addLabelAt(label3, xOff2 + 200,i * 40 - 8);
            i++;
        }
        panel.count+=10;
    }

    public void createArmorFields(final OrderedPanel panel, ItemTier tier, int level){
        int xOff = 20;
        int xOff1 = 80;
        int xOff2 = 300;
        int xOff3 = 480;
        int a = 60;
        int xOff4 = xOff3 + a;
        int xOff5 = xOff4 + 85;
        int xOff6 = xOff5 + a + 30;
        int i = 1;
        panel.addField("Health","health","" + tier.getHealth(level),Converters.INT);
        panel.addField("Armor Color","armorColor","160,101,64",Converters.COLOR);
        panel.addSelectionField("Armor Type","armorType",new String[]{"Leather","Golden","Chain","Iron","Diamond"},Converters.RAW);
        panel.addSelectionField("Class Requirement","classRequirement",new String[]{"null","Mage","Warrior","Archer","Assassin"},Converters.RAW);
        final String[] strings = new String[]{"Thunder","Fire","Water","Air","Earth"};
        String[] weak = new String[]{"Earth","Water","Thunder","Fire","Air"};
        String[] strong = new String[]{"Water","Air","Fire","Earth","Thunder"};
        for (String element : strings){
            String unlocal = element.toLowerCase();
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(TexturedButton.class.getResource(unlocal + ".png")));
            label.setSize(new Dimension(32, 32));
            panel.addLabelAt(label, xOff, i * 40 - 8);
            panel.addFieldAt(element + " Defense",unlocal + "Defense", "0", Converters.ELEMENT_DEF, xOff1,i * 40);
            Converters.addElement(element,panel);
            JLabel label1 = new JLabel();
            label1.setIcon(new ImageIcon(TexturedButton.class.getResource(weak[i-1].toLowerCase() + ".png")));
            label1.setSize(new Dimension(32,32));
            JLabel label2 = new JLabel("Weak To:");
            int width1 = (int) label2.getPreferredSize().getWidth();
            label2.setSize(new Dimension(width1,18));
            JLabel label3 = new JLabel();
            label3.setIcon(new ImageIcon(TexturedButton.class.getResource(strong[i-1].toLowerCase() + ".png")));
            label3.setSize(new Dimension(32,32));
            JLabel label4 = new JLabel("Strong Against:");
            int width2 = (int) label4.getPreferredSize().getWidth();
            label4.setSize(new Dimension(width2,18));
            panel.addLabelAt(label2, xOff2,i * 40);
            panel.addLabelAt(label1, xOff2 + 60,i * 40 - 8);
            panel.addLabelAt(label4, xOff2 + 100,i * 40);
            panel.addLabelAt(label3, xOff2 + 200,i * 40 - 8);
            i++;
        }
        panel.count+=10;



    }

    public void createExtraFields(OrderedPanel panel, int level){
        int xOff = 20;
        int xOff1 = 80;
        int xOff2 = 300;
        int xOff3 = 500;
        int a = 60;
        int xOff4 = xOff3 + a;
        int xOff5 = xOff4 + 65;
        int xOff6 = xOff5 + a + 30;
        int i = 1;
        //required stats
        panel.addField("Required Quest","quest","null",Converters.RAW);
        panel.addField("Required Strength","strength","0",Converters.INT);
        panel.addField("Required Dexterity","dexterity","0",Converters.INT);
        panel.addField("Required Intelligence","intelligence","0",Converters.INT);
        panel.addField("Required Defense","defense","0",Converters.INT);
        panel.addField("Required Agility","agility","0",Converters.INT);
        //ids
        panel.addField("Mana Regen","manaRegen", "0",Converters.INT);
        panel.addField("Spell Damage","spellDamage", "0",Converters.INT);
        panel.addField("Damage Bonus","damageBonus", "0",Converters.INT);
        panel.addField("Life Steal","lifeSteal", "0",Converters.INT);
        panel.addField("Mana Steal","manaSteal", "0",Converters.INT);
        panel.addField("XP Bonus","xpBonus", "0",Converters.INT);
        panel.addField("Loot Bonus","lootBonus", "0",Converters.INT);
        panel.addField("Reflection","reflection", "0",Converters.INT);
        panel.addField("Health Regen","healthRegen","0",Converters.INT);
        //extra ids
        panel.addField("Thorns","thorns", "0",Converters.INT);
        panel.addField("Exploding","exploding", "0",Converters.INT);
        panel.addField("Speed","speed", "0",Converters.INT);
        panel.addField("Attack Speed Bonus","attackSpeedBonus", "0",Converters.INT);
        panel.addField("Poison","poison", "0",Converters.INT);
        panel.addField("Health Bonus","healthBonus", "0",Converters.INT);
        panel.addField("Soul Points","soulPoints", "0",Converters.INT);
        panel.addField("Emerald Stealing","emeraldStealing", "0",Converters.INT);
        //stats bonus
        panel.addField("Bonus Strength","strengthPoints","0",Converters.INT);
        panel.addField("Bonus Dexterity","dexterityPoints","0",Converters.INT);
        panel.addField("Bonus Intelligence","intelligencePoints","0",Converters.INT);
        panel.addField("Bonus Defense","defensePoints","0",Converters.INT);
        panel.addField("Bonus Agility","agilityPoints","0",Converters.INT);

        final String[] strings = new String[]{"Thunder","Fire","Water","Air","Earth"};
        String[] weak = new String[]{"Earth","Water","Thunder","Fire","Air"};
        String[] strong = new String[]{"Water","Air","Fire","Earth","Thunder"};
        for (String element : strings){
            String unlocal = element.toLowerCase();
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(TexturedButton.class.getResource(unlocal + ".png")));
            label.setSize(new Dimension(32, 32));
            panel.addLabelAt(label, xOff, i * 40 - 8);
            panel.addFieldAt(element + " Damage Bonus", "bonus" + element + "Damage", "0", Converters.ELEMENT_DEF, xOff1, i * 40);
            panel.addFieldAt(element + " Defense Bonus", "bonus" + element + "Defense", "0", Converters.ELEMENT_DEF, xOff2,i * 40);
            Converters.addElement(element, panel);
            JLabel label1 = new JLabel();
            label1.setIcon(new ImageIcon(TexturedButton.class.getResource(weak[i-1].toLowerCase() + ".png")));
            label1.setSize(new Dimension(32,32));
            JLabel label2 = new JLabel("Weak To:");
            int width1 = (int) label2.getPreferredSize().getWidth();
            label2.setSize(new Dimension(width1,18));
            JLabel label3 = new JLabel();
            label3.setIcon(new ImageIcon(TexturedButton.class.getResource(strong[i-1].toLowerCase() + ".png")));
            label3.setSize(new Dimension(32,32));
            JLabel label4 = new JLabel("Strong Against:");
            int width2 = (int) label4.getPreferredSize().getWidth();
            label4.setSize(new Dimension(width2,18));
            panel.addLabelAt(label2, xOff3,i * 40);
            panel.addLabelAt(label1, xOff4,i * 40 - 8);
            panel.addLabelAt(label4, xOff5,i * 40);
            panel.addLabelAt(label3, xOff6,i * 40 - 8);
            i++;
        }


        panel.frame.repaint();
    }


}
