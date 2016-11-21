import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PriorityQueue<E> {

    private ArrayList<E> binaryHeap = new ArrayList<>();
    private Comparator<? super E> cmp;
    private Map<E, Integer> placeMap = new HashMap<>();

    public PriorityQueue (Comparator<? super E> cmp){
        this.cmp=cmp;
    }

    public void insert(E bid) {
        int insertAt;
        for(insertAt = 0; insertAt < binaryHeap.size(); insertAt++) {
            if(cmp.compare(e, binaryHeap.get(insertAt)) > 0) {
                break;
            }
        }
        binaryHeap.add(insertAt, e);
        placeMap.put(e, binaryHeap.size() - 1);
    }

    public E extract(E bid) {
        int extractAt;
        for(extractAt = binaryHeap.size(); extractAt > 0; extractAt--){
            if(cmp.compare(bid, binaryHeap.get(extractAt)) > 0){
                break;
            }
        }
        return binaryHeap.get(extractAt);


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
