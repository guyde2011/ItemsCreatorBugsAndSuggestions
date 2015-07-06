package items.components;

import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

/**
 * Created by CH on 05/07/2015.
 */
public interface AbstractField {

    public String getRawText();

    public FieldConverter getConverter();

    public void setKeyListener(KeyListener listener);

    public void addFocusListener(FocusListener listener);
}
