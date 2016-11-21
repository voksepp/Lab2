import java.util.Comparator;

public class Bid {
    private final String name;
    private final int value;
    private final int oldValue;
    private final String type;

    public Bid(String name, String type, int value){
        this.name = name;
        this.value = value;
        this.type = type;
        oldValue = -1;
    }
    public Bid(String name, String type, int value, int oldValue){
        this.name = name;
        this.value = value;
        this.type = type;
        this.oldValue = oldValue;
    }

    public int getValue() {
        return value;
    }
    public String getName(){
        return name;
    }

    public String getType{
        return type;
    }

}
