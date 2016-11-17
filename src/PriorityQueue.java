import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class PriorityQueue<E> {

    private ArrayList<E> binaryHeap = new ArrayList<E>();
    private Map<String, Integer> placeMap;
    private Comparator<? super E> cmp;

    public PriorityQueue (Comparator<? super E> cmp){
        this.cmp=cmp;
    }

    public void insert(E bid) {
        for(int insertAt = 0; insertAt < binaryHeap.size(); insertAt++) {
            if(cmp.compare(bid, binaryHeap.get(insertAt)) > 0) {
                break;
            }
        }
        binaryHeap.add(insBefore, bid);
    }

    private void shiftUp(){
        int k = binaryHeap.size() - 1;
        while (k>0){
            int p = (k-1)/2;
            E item = binaryHeap.get(k);
            E parent = binaryHeap.get(p);
            if(/*item.compareTo(parent) > 0*/) {
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

    //public ArrayList<Bid> getSellist() {
        //return Sellist;
    //}

    //public ArrayList<Bid> getBuyList(){
        //return BuyList;
    //}
}
