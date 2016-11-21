import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 *
 * @param <E>
 */
public class PriorityQueue<E> {

    private ArrayList<E> binaryHeap = new ArrayList<>();
    private Comparator<? super E> cmp;
    private Map<E, Integer> placeMap = new HashMap<>();

    /**
     * Constructor for the priority queue
     * @param cmp The Comparator that will be used to determine the order of objects in the queue
     */
    public PriorityQueue (Comparator<? super E> cmp){
        this.cmp=cmp;
    }

    /**
     * This method inserts an object in the priority queue and places it in the appropriate position
     * @param e An object
     */
    public void insert(E e) {
        if(binaryHeap.size() > 0) {
            binaryHeap.add(binaryHeap.size() , e);
            placeMap.put(e, binaryHeap.indexOf(e));
        }
        else {
            binaryHeap.add(e);
            placeMap.put(e,0);
        }
        bubbleUp(binaryHeap.size() - 1);
    }

    /**
     *
     * @param
     */
    public void replace(E o, E n){
        int i = binaryHeap.indexOf(o);
        extract(i);
        insert(n);
    }

    /**
     *
     * @param index
     * @return
     */
    public E extract(int index) {
        while (index < binaryHeap.size() - 1) {
            if(rightChild(index) != -1){
                if (cmp.compare(binaryHeap.get(leftChild(index)), binaryHeap.get(rightChild(index))) > 0){
                        swap(index, leftChild(index));
                        index = leftChild(index);
                }
                else if (cmp.compare(binaryHeap.get(leftChild(index)), binaryHeap.get(rightChild(index))) <= 0){
                        swap(index, rightChild(index));
                        index = rightChild(index);
                }
            }
            else if (leftChild(index) != -1){
                    swap(index, leftChild(index));
                    index = leftChild(index);
            }
            else{
                break;
            }
        }
        int lastIndex = binaryHeap.size()-1;
        E tmp = binaryHeap.get(lastIndex);
        binaryHeap.remove(lastIndex);
        placeMap.remove(tmp);
        return tmp;
}

    /**
     *
     * @param index
     */
    public void bubbleUp(int index){
        while(index > 0 && cmp.compare(binaryHeap.get(index), binaryHeap.get(parent(index))) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     *bubbleDown takes the index of a bid and puts it in the correct position of the binary heap
     * @param index the index of the bid
     */
    public void bubbleDown(int index) {
        while (index < binaryHeap.size() - 1) {
            if(rightChild(index) != -1){
               if (cmp.compare(binaryHeap.get(leftChild(index)), binaryHeap.get(rightChild(index))) > 0){
                   if(cmp.compare(binaryHeap.get(index),binaryHeap.get(leftChild(index))) < 0){
                       swap(index, leftChild(index));
                       index = leftChild(index);
                   }
               }
               else if (cmp.compare(binaryHeap.get(leftChild(index)), binaryHeap.get(rightChild(index))) <= 0){
                   if(cmp.compare(binaryHeap.get(index), binaryHeap.get(rightChild(index))) < 0){
                       swap(index, rightChild(index));
                       index = rightChild(index);
                   }
               }
            }
            else if (leftChild(index) != -1){
                if (cmp.compare(binaryHeap.get(index), binaryHeap.get(leftChild(index))) < 0){
                    swap(index, leftChild(index));
                    index = leftChild(index);
                }
            }
            else{
                break;
            }
        }

    }

    /**
     *
     * @param i1 the first index
     * @param i2 the second index
     */
    private void swap(int i1, int i2){
        E e1 = binaryHeap.get(i1);
        E e2 = binaryHeap.get(i2);

        placeMap.replace(e1, i2);
        placeMap.replace(e2, i1);

        binaryHeap.set(i1, e2);
        binaryHeap.set(i2, e1);
    }

    /**
     *
     * @param i index of child
     * @return index of parent
     */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     *
     * @param i index of parent
     * @return index of the left child
     */
    private int leftChild(int i) {
        int childIndex = 2*i + 1;
        if(binaryHeap.size() -1 >= childIndex)
            return childIndex;
        else return -1;
    }

    /**
     *
     * @param i index of parent
     * @return index of the right child
     */
    private int rightChild(int i) {
        int childIndex = 2*i + 2;
        if(binaryHeap.size() -1 >= childIndex)
            return childIndex;
        else return -1;
    }

    /**
     *
     * @return element at the front of the queue
     */
    public E peek(){
        return binaryHeap.get(0);
    }

    public E pop(){
        return extract(0);
    }
    @Override
    public String toString () {
        String elements = new String();
        for (E e: binaryHeap){
            elements = elements + e.toString();
        }
        if(elements.length()<=2)
            return "";
        return elements.substring(0, elements.length() - 2);
    }
    public int getSize () {
        return binaryHeap.size();
    }
}