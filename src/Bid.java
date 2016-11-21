import java.util.Comparator;

public class Bid {
    private final String name;
    private int value;
    private int oldValue;
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
    public int getOldValue(){
        return oldValue;
    }
    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }
    public void updateValue (int newValue, int oldValue){
        if(value == oldValue){
            this.oldValue = oldValue;
            value = newValue;
        }
        else
            System.out.println("Ogiltig ändring av bud, tidigare värde ej korrekt");
    }
    @Override
    public String toString(){
        return name + " " + value + ", ";
    }
}
