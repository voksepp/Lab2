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

    public void insert(E e) {
        binaryHeap.add(e);
        placeMap.put(e, binaryHeap.size() - 1);
        bubbleUp(binaryHeap.size() - 1);
    }

    //FIXME: Gör så att extract ändrar i placemap också
    public E extract(E bid) {
        int extractAt;
        for (extractAt = binaryHeap.size(); extractAt > 0; extractAt--) {
            if (cmp.compare(bid, binaryHeap.get(extractAt)) > 0) {
                break;
            }
        }
        return binaryHeap.get(extractAt);
    }

    public void bubbleUp(int index){
        while(index > 0 && cmp.compare(binaryHeap.get(index), binaryHeap.get(parent(index))) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }
    private void swap(int i1, int i2){
        E e1 = binaryHeap.get(i1);
        E e2 = binaryHeap.get(i2);

        placeMap.replace(e1, i2);
        placeMap.replace(e2, i1);

        binaryHeap.set(i1, e2);
        binaryHeap.set(i2, e1);
    }
    private int parent(int i) {
        return (i - 1) / 2;
    }
    private int leftChild(int i) {
        return 2*i + 1;
    }
    private int rightChild(int i) {
        return 2*i + 2;
    }
    public E peek(){
        return binaryHeap.get(0);
    }
    public E pop(){
        E temp = binaryHeap.get(0);
        placeMap.remove(binaryHeap.get(0));
        binaryHeap.remove(0);
        return temp;
    }
}
