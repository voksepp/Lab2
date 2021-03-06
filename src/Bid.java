/**
 * A bid, contains the name of the seller/buyer, the type of bid (sell/buy),
 * and the value of the bid. If the value of the bid is changed, it will
 * also contain the old value.
 */
class Bid {
    private final String name;
    private final String type;
    private int value;
    private int oldValue;

    /**
     * Constructor for a new bid
     *
     * @param name  is the name of the bidder
     * @param type  is the type of bid (sell, buy)
     * @param value is the bid amount
     */
    Bid(String name, String type, int value) {
        this.name = name;
        this.value = value;
        this.type = type;
        oldValue = -1;
    }

    /**
     * Constructor for a changed bid
     *
     * @param name     is the name of the bidder
     * @param type     is the type of bid (change a sell bid, change a buy bid)
     * @param value    is the bid amount
     * @param oldValue is the old bid amount that is to be changed
     */
    Bid(String name, String type, int value, int oldValue) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.oldValue = oldValue;
    }

    /**
     * @return returns the value of a bid
     */
    int getValue() {
        return value;
    }

    /**
     * @return returns the value of the old bid
     */
    int getOldValue() {
        return oldValue;
    }

    /**
     * @return returns the name of the bidder
     */
    String getName() {
        return name;
    }

    /**
     * @return returns the bid type
     */
    String getType() {
        return type;
    }

    /**
     * Updates the value of a bid, if the old value given is correct
     *
     * @param newValue is the value that the bid is to be changed to
     * @param oldValue is the current value of the bid
     */
    void updateValue(int newValue, int oldValue) {
        if (value == oldValue) {
            this.oldValue = oldValue;
            value = newValue;
        } else
            System.out.println("Ogiltig ändring av bud, tidigare värde ej korrekt");
    }

    /**
     * @return returns a string with the name of the bidder and
     * the value of their bid followed by a comma
     */
    @Override
    public String toString() {
        return name + " " + value + ", ";
    }
}
