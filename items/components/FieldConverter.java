package items.components;

/**
 * Created by CH on 05/07/2015.
 */
public class FieldConverter {

    private String name;
    private YMLDataConverter converter;

    public FieldConverter(String name, YMLDataConverter converter){
        this.converter = converter;
        this.name = name;
    }

    public boolean shouldCheck(AbstractField field){
        return converter.shouldConvert(field);
    }
    public String getName(){
        return name;
    }

    public String getYMLData(AbstractField field){
        return converter.getData(field);
    }

    public YMLDataConverter getConverter(){
        return converter;
    }
}
