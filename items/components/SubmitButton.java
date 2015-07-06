package items.components;

import javax.swing.*;

/**
 * Created by CH on 05/07/2015.
 */
public class SubmitButton extends TexturedButton {

    private Submitable sub;

    public SubmitButton(String img) {
        super(img);
    }

    public SubmitButton(ImageIcon img) {
        super(img);
    }

    public SubmitButton setSubmit(Submitable submit){
        sub = submit;
        return this;
    }

    public void onClick(){
        sub.onSubmit();
    }
}
