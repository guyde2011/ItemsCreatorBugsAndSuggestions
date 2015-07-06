package items.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public abstract class TexturedButton extends JButton{
	public TexturedButton(String img) {
		ImageIcon icon = getIcon((img));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setIcon(icon);
        this.setPressedIcon(getIcon(("pressed_" + img)));
		setMargin(new Insets(0, 0, 0, 0));
		setIconTextGap(0);
		setBorderPainted(false);
		setBorder(null);
		setText(null);
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
		this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                onClick();
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

	public TexturedButton(ImageIcon icon) {
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
				onClick();
			}
		});
	}
	
	public abstract void onClick();
}
