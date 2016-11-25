import java.util.Comparator;

/**
 * Compares buy Bid elements
 */
class BuyComparator implements Comparator<Bid>{

    public int compare(Bid a, Bid b) {
        return b.getValue()-a.getValue();
    }
}
