package items.components;

/**
 * Created by CH on 05/07/2015.
 */
public abstract class YMLDataConverter {
    public abstract String getData(AbstractField field);

    public abstract void applyData(AbstractField field);

    public boolean shouldConvert(AbstractField field){
        return true;
    }
}
