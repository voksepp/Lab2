public class Bid {
    private final String name;
    private int value;
    private int oldValue;
    private final String type;

    /**
     *
     * @param name is the name of the bidder
     * @param type is the type of bid (sell, buy)
     * @param value is the bid amount
     */
    public Bid(String name, String type, int value){
        this.name = name;
        this.value = value;
        this.type = type;
        oldValue = -1;
    }

    /**
     *
     * @param name is the name of the bidder
     * @param type is the type of bid (change a sell bid, change a buy bid)
     * @param value is the bid amount
     * @param oldValue is the old bid amount that is to be changed
     */
    public Bid(String name, String type, int value, int oldValue){
        this.name = name;
        this.value = value;
        this.type = type;
        this.oldValue = oldValue;
    }

    /**
     *
     * @return returns the value of a bid
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @return returns the value of the old bid
     */
    public int getOldValue(){
        return oldValue;
    }

    /**
     *
     * @return returns the name of the bidder
     */
    public String getName(){
        return name;
    }

    /**java -ea -jar testing.jar lab2test.Lab2GenTest Lab2

     *
     * @return returns the bid type
     */
    public String getType(){
        return type;
    }

    /**
     *
     * @param newValue is the value that the bid is to be changed to
     * @param oldValue is the current value of the bid
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
     * @return returns a string with the name of the bidder and
     * the value of their bid followed by followed by a comma
     */
    @Override
    public String toString(){
        return name + " " + value + ", ";
    }
}
