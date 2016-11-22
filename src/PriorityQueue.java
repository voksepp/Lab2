import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @param <E> This class can take a generic element type E
 */
class PriorityQueue<E> {

    private final ArrayList<E> binaryHeap = new ArrayList<>();
    private final Comparator<? super E> cmp;
    private final Map<E, Integer> placeMap = new HashMap<>();

    /**
     * Constructor for the priority queue
     *
     * @param cmp The Comparator that will be used to determine the order of objects in the queue
     */
    PriorityQueue(Comparator<? super E> cmp) {
        this.cmp = cmp;
    }

    /**
     * This method inserts an object in the priority queue and places it in the appropriate position
     *
     * @param e An object
     */
    void insert(E e) {
        binaryHeap.add(e);
        placeMap.put(e, binaryHeap.size()-1);
        bubbleUp(placeMap.get(e));
    }

    /**
     * Replaces //TODO: skriv
     *
     * @param o the element that is to be removed
     * @param n the element that is to replace the old element
     */
    void replace(E o, E n) {
        int i = placeMap.get(o);
        removeElement(i);
        insert(n);
    }

    /** //TODO:SKRIV
     * @param index is the index of the bid that should be bubbled up
     */
    private void bubbleUp(int index) { // time complexity log(n)
        while (index > 0 && cmp.compare(binaryHeap.get(index), binaryHeap.get(parent(index))) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    /** //TODO:SKRIV
     * @param i is the index of the bid that should be bubbled down
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
     * @return calls the removeElement method with index 0 as input parameter
     */
    E removeFirst() {
        return removeElement(0);
    }

    /**
     * @param i is the index of the bid that is to be removed
     * @return returns a generic Element E
     */
    private E removeElement(int i) {
        swap(i, binaryHeap.size() - 1);
        E temp = binaryHeap.remove(binaryHeap.size() - 1);
        bubbleDown(i);
        return temp;
    }

    /**
     * @param i1 the first index
     * @param i2 the second index
     */
    private void swap(int i1, int i2) { // time complexity O(1)
        E e1 = binaryHeap.get(i1);
        E e2 = binaryHeap.get(i2);

        placeMap.replace(e1, i2);
        placeMap.replace(e2, i1);

        binaryHeap.set(i1, e2);
        binaryHeap.set(i2, e1);
    }

    /**
     * @param i index of child
     * @return index of parent
     */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * @param i index of parent
     * @return index of the left child
     */
    private int leftChild(int i) {
        int childIndex = 2 * i + 1;
        if (binaryHeap.size() - 1 >= childIndex)
            return childIndex;
        else return -1;
    }

    /**
     * @param i index of parent
     * @return index of the right child
     */
    private int rightChild(int i) {
        int childIndex = 2 * i + 2;
        if (binaryHeap.size() - 1 >= childIndex)
            return childIndex;
        else return -1;
    }

    /**
     * @return element at the front of the queue
     */
    E peek() {
        return binaryHeap.get(0);
    }

    @Override
    public String toString() {
        String elements = "";

        while (binaryHeap.size() > 0)
            elements = elements + removeFirst().toString();

        if (elements.length() <= 2)
            return "";
        return elements.substring(0, elements.length() - 2);
    }

    /**
     * @return the size of the priority queue
     */
    int size() {
        return binaryHeap.size();
    }
}