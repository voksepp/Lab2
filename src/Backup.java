public class Backup {

    /**
     *heapify takes the index of a bid and puts it in the correct position of the binary heap
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
                if (cmp.compare(binaryHeap.get(index), binaryHeap.get(leftChild(index))) > 0){
                    swap(index, leftChild(index));
                    index = leftChild(index);
                }
            }
            else{
                break;
            }
        }

    }
}
