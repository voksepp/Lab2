import java.util.Comparator;

public class BuyComparator implements Comparator<Bid>{

    public int compare(Bid a, Bid b) {
        if(a.getValue() < b.getValue()) {
            return -1;
        } else if(a.getValue() > b.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
