public class Bid {
    private final String name;
    private int value;
    private int oldValue;
    private final String type;

    /**
     *
     * @param name
     * @param type
     * @param value
     */
    public Bid(String name, String type, int value){
        this.name = name;
        this.value = value;
        this.type = type;
        oldValue = -1;
    }

    /**
     *
     * @param name
     * @param type
     * @param value
     * @param oldValue
     */
    public Bid(String name, String type, int value, int oldValue){
        this.name = name;
        this.value = value;
        this.type = type;
        this.oldValue = oldValue;
    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @return
     */
    public int getOldValue(){
        return oldValue;
    }

    /**
     *
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return
     */
    public String getType(){
        return type;
    }

    /**
     *
     * @param newValue
     * @param oldValue
     */
    public void updateValue (int newValue, int oldValue){
        if(value == oldValue){
            this.oldValue = oldValue;
            value = newValue;
        }
        else
            System.out.println("Ogiltig ändring av bud, tidigare värde ej korrekt");
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return name + " " + value + ", ";
    }
}
