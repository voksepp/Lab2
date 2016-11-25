import java.util.Comparator;

/**
 * Compares buy Bid elements
 */
class BuyComparator implements Comparator<Bid>{

    public int compare(Bid a, Bid b) {
        return a.getValue()-b.getValue();
    }
}
