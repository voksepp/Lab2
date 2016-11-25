import java.util.Comparator;

/**
 * Compares sell Bid elements
 */
class SellComparator implements Comparator<Bid>{

    public int compare(Bid a, Bid b) {
        return b.getValue()-a.getValue();
    }
}
