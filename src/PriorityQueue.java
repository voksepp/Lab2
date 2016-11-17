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

    private void shiftUp(){
        int k = binaryHeap.size() - 1;
        while (k>0){
            int p = (k-1)/2;
            Bid item = binaryHeap.get(k);
            Bid parent = binaryHeap.get(p);
            if(item.compareTo(parent) > 0) {
                //swap
                binaryHeap.set(k,parent);
                binaryHeap.set(p,item);
                //move up one level
                k=p;
            } else{
                break;
            }

        }
    }
}
