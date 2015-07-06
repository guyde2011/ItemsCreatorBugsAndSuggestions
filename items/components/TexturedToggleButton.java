package items.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public abstract class TexturedToggleButton extends JToggleButton{
    private ImageIcon iconOn;
    private ImageIcon iconOff;
    private boolean state = false;

	public TexturedToggleButton(String img) {
		ImageIcon icon = getIcon((img));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        iconOff = icon;
        setIcon(icon);
		setMargin(new Insets(0, 0, 0, 0));
		setIconTextGap(0);
		setBorderPainted(false);
		setBorder(null);
		setText(null);
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
        iconOn = getIcon(("pressed_" + img));
        setSelectedIcon(iconOn);
		this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                onClick();
                state = !state;
            }
        });
	}


	private ImageIcon getIcon(String str){
		URL  imgURL = OrderedPanel.class.getResource(str);
		if (imgURL != null) {
            return new ImageIcon(imgURL);
		}
		else {
			System.err.println("Couldn't find image file: " + str);
			return null;
		}
	}

	public TexturedToggleButton(ImageIcon icon) {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
		setIcon(icon);
		setMargin(new Insets(0, 0, 0, 0));
		setIconTextGap(0);
		setBorderPainted(false);
		setBorder(null);
		setText(null);
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
                state = !state;
                if (state){
                    setIcon(iconOn);
                } else {
                    setIcon(iconOff);
                }
                onClick();
			}
		});
	}

    public boolean getState(){
        return state;
    }

	public abstract void onClick();
}
