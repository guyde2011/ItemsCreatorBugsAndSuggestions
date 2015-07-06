package items.utils;

/**
 * Created by CH on 05/07/2015.
 */
public class Pair<A,B> {

    private A first;

    private B second;

    public Pair(A obj1, B obj2){
        first = obj1;
        second = obj2;
    }

    public A getFirst(){
        return first;
    }

    public B getSecond(){
        return second;
    }
}
