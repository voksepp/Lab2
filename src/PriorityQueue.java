import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class PriorityQueue<E> {

    private ArrayList<E> binaryHeap = new ArrayList<E>();
    private Comparator<? super E> cmp;

    //private Map<String, Integer> placeMap;

    public PriorityQueue (Comparator<? super E> cmp){
        this.cmp=cmp;
    }

    public void insert(E bid) {
        int insertAt;
        for(insertAt = 0; insertAt < binaryHeap.size(); insertAt++) {
            if(cmp.compare(bid, binaryHeap.get(insertAt)) > 0) {
                break;
            }
        }
        binaryHeap.add(insertAt, bid);
    }
}
