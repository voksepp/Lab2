import java.util.*;

/**
 *
 * @param <E>
 */
public class PriorityQueue<E> {

    private final ArrayList<E> binaryHeap = new ArrayList<>();
    private final Comparator<? super E> cmp;
    private final Map<E, Integer> placeMap = new HashMap<>();

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
        binaryHeap.add(e);
        placeMap.put(e, binaryHeap.indexOf(e));
        bubbleUp(binaryHeap.indexOf(e));
    }

    /**
     *
     * @param o
     * @param n
     */
    public void replace(E o, E n){
        int i = binaryHeap.indexOf(o);
        removeElement(i);
        insert(n);
    }

    /**
     *
     * @param index
     */
    private void bubbleUp(int index){
        while(index > 0 && cmp.compare(binaryHeap.get(index), binaryHeap.get(parent(index))) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     *
     * @param i
     */
    private void bubbleDown(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int min;

        if ((left != -1) && cmp.compare(binaryHeap.get(left), binaryHeap.get(i)) > 0)
            min = left;
        else
            min = i;

        if ((right != -1) && cmp.compare(binaryHeap.get(right), binaryHeap.get(min)) > 0)
            min = right;

        if (min != i) {
            swap(i, min);
            bubbleDown(min);
        }
    }

    /**
     *
     * @return calls the removeElement method with index 0 as input parameter
     */
    public E removeFirst(){
        return removeElement(0);
    }

    /**
     *
     * @param i
     * @return
     */
    private E removeElement(int i){
        swap (i, binaryHeap.size()-1);
        E temp = binaryHeap.remove(binaryHeap.size()-1);
        bubbleDown(i);
        return temp;
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

    @Override
    public String toString () {
        String elements = "";

        while(binaryHeap.size() > 0)
            elements = elements + removeFirst().toString();

        if(elements.length()<=2)
            return "";
        return elements.substring(0, elements.length() - 2);
    }

    /**
     *
     * @return the size of the priority queue
     */
    public int size() {
        return binaryHeap.size();
    }
}