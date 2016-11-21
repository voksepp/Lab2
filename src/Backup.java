


    /*
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

    public E removeElement(int index){
        while (index <= (binaryHeap.size() - 1)) {
            if(rightChild(index) != -1){
                swap(index, rightChild(index));
                index = rightChild(index);
            }
            else if (leftChild(index) != -1){
                swap(index, leftChild(index));
                index = leftChild(index);
            }
        }

        int lastIndex = binaryHeap.size()-1;

        E tmp = binaryHeap.get(lastIndex);

        binaryHeap.remove(lastIndex);
        placeMap.remove(tmp);

        heapify(0);

        return tmp;
    }
}
*/