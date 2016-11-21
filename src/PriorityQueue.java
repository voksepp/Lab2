import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PriorityQueue<E> {

    private ArrayList<E> binaryHeap = new ArrayList<>();
    private Comparator<? super E> cmp;
    private Map<String, Integer> placeMap = new HashMap<>();

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
        placeMap.put(E.toString(),E.getValue());
    }

    public Bid extract(E bid) {
        for(int i = binaryHeap.size(); i <= 0; i--){
            if(cmp.compare(bid, binaryHeap.get(i) > 0)){
                break;
            }
        }
        return binaryHeap.get(i);


        /*int index = 1;

        // bubble down
        while (hasLeftChild(index)) {
            // which of my children is smaller?
            int smallerChild = leftIndex(index);

            // bubble with the smaller child, if I have a smaller child
            if (hasRightChild(index)
                    && array[leftIndex(index)].compareTo(array[rightIndex(index)]) > 0) {
                smallerChild = rightIndex(index);
            }

            if (array[index].compareTo(array[smallerChild]) > 0) {
                swap(index, smallerChild);
            } else {
                // otherwise, get outta here!
                break;
            }

            // make sure to update loop counter/index of where last el is put
            index = smallerChild;
        }*/
    }
}
