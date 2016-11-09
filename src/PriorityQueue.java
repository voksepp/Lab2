import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class PriorityQueue<E> {

    private ArrayList<Bid> binaryHeap;
    private Map<String, Integer> placeMap;
    private Comparator cmp;

    public PriorityQueue (Comparator<? super E> cmp){
        this.cmp=cmp;
    }
}
