import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

// THIS IS WHERE EVERYTHING HAPPENS
// AUTHOR: DANIEL RAMOS

public class Sorts {
    private final int height;
    private final int width;
    private final SortingAlgos sortingAlgos;
    private final InsertionSort insertionSort;
    private final BubbleSort bubbleSort;
    private final QuickSort quickSort;
    private int[] numbers;
    private int[] oldNumbers;
    private int count_size;
    private int size;
    private int length;
    private int i;
    private int j;
    private int k;
    private int ticks;
    private int counterForMerge;
    private int startiMerge;
    private int startjMerge;
    private int tempForMerge;
    private boolean insertionSortBol, bubbleSortBol, quickSortBol, sorting, lowerSize, upSize, faster, slower, old, mergeSort, shiftingForMerge;


    public Sorts(int size, int width, int height, SortingAlgos sortingAlgos) {
        numbers = new int[400];
        oldNumbers = new int[400];
        insertionSort = new InsertionSort(0, 0, numbers, this);
        bubbleSort = new BubbleSort(0, 0, numbers, this);
        quickSort = new QuickSort(0, 0, 0, numbers, this);
        this.sortingAlgos = sortingAlgos;
        this.size = size;
        this.height = height;
        this.width = width;
        length = width / size;
        randomizeArraySame();
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void setK(int k) {
        this.k = k;
    }

    private void createArrayOrdered(int[] array) {
        for (int i = 0; i < size; i++)
            array[i] = i;
    }

    public void randomizeArraySame() {
        randomizeArray(numbers);
        oldNumbers = numbers.clone();
        ticks = 0;
        k = -1;
        i = -1;
        j = -1;
        sorting = false;
        insertionSortBol = false;
        bubbleSortBol = false;
        quickSortBol = false;
        mergeSort = false;
    }

    private void randomizeArray(int[] array) {
        createArrayOrdered(array);
        for (int i = 0; i < size; i++)
            exchange(array, i, ThreadLocalRandom.current().nextInt(0, size));
    }

    private void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void setOld(boolean old) {
        this.old = old;
    }

    private void setSize(int length, boolean growing) {
        if (length < 2 || length > 50)
            return;

        while (800 / (double) (length) % 1 != 0) {
            if (growing)
                length++;
            else
                length--;
        }

        this.length = length;
        size = width / length;
        randomizeArraySame();
    }

    public void setSorting(boolean sorting) {
        if (!sorting) {
            i = -1;
            j = -1;
            k = -1;
            this.sorting = false;
            insertionSortBol = false;
            bubbleSortBol = false;
            quickSortBol = false;
            mergeSort = false;
        }
    }

    public void setFaster(boolean faster) {
        this.faster = faster;
    }

    public void setSlower(boolean slower) {
        this.slower = slower;
    }

    public void setLowerSize(boolean lowerSize) {
        this.lowerSize = lowerSize;
    }

    public void setUpSize(boolean upSize) {
        this.upSize = upSize;
    }

    public void setMergeSort(boolean mergeSort) {
        if (!sorting) {
            this.mergeSort = mergeSort;
            sorting = true;
            count_size = 1;
            i = 0;
            j = 1;
            startiMerge = 0;
            startjMerge = 1;
        }
    }

    public void setInsertionSortBol(boolean insertionSortBol) {
        if (!sorting) {
            this.insertionSortBol = insertionSortBol;
            sorting = true;
            insertionSort.setAll(size, numbers);
        }
    }

    public void setBubbleSortBol(boolean bubbleSortBol) {
        if (!sorting) {
            this.bubbleSortBol = bubbleSortBol;
            sorting = true;
            bubbleSort.setAll(size, numbers);
        }
    }

    public void setQuickSortBol(boolean quickSortBol) {
        if (!sorting) {
            this.quickSortBol = quickSortBol;
            quickSort.setAll(size, numbers);
            sorting = true;
        }
    }

    // MERGE SORT
    private void iterateMergeSort() {
        ticks++;
        if (count_size > size) {
            mergeSort = false;
            sorting = false;
            i = -1;
            j = -1;
        }
        //OPTIMIZED FOR FIRST
        else if (count_size == 1) {
            if (j >= size) {
                count_size *= 2;
                i = 0;
                j = count_size;
                startiMerge = 0;
                startjMerge = count_size;
            } else if (numbers[i] > numbers[j]) {
                exchange(numbers, i, j);
            } else {
                i += 2;
                j += 2;
            }
        }
        // IF J ENDS
        else if (j >= startjMerge + count_size || j >= size) {
            if (j < size && numbers[i] > numbers[j])
                exchange(numbers, i, j);
            int double_count = count_size * 2;
            i = startiMerge + double_count;
            j = startjMerge + double_count;
            startiMerge = i;
            startjMerge = j;
            if (i > size) {
                count_size *= 2;
                i = 0;
                j = count_size;
                startiMerge = 0;
                startjMerge = j;
            }
        }
        // SHIFTING FOR WHEN num[i] > num[j] happens
        else if (shiftingForMerge) {
            if (counterForMerge > 0) {
                numbers[i + counterForMerge] = numbers[i + counterForMerge - 1];
                counterForMerge--;
            } else {
                shiftingForMerge = false;
                numbers[i] = tempForMerge;
                i++;
                j = Math.min(j + 1, startjMerge + count_size - 1);
            }
        }
        // IF LEFT SIDE ENDS
        else if (i == j) {
            int double_count = count_size * 2;
            i = startiMerge + double_count;
            j = startjMerge + double_count;
            startiMerge = i;
            startjMerge = j;
        }
        // num[i] < num[j]
        else if (numbers[i] < numbers[j]) {
            i++;
        }
        // num[i] > num[j]
        else if (numbers[i] > numbers[j]) {
            tempForMerge = numbers[j];
            counterForMerge = j - i;
            shiftingForMerge = true;
        } else
            j = Math.min(Math.min(j + 1, startjMerge + count_size), size);

    }


    // RENDER AND UPDATE
    public void render(Graphics g) {
        double multiplier = length == 2 ? 1.6 : length - 1;

        g.setColor(Color.GREEN);
        for (int i = 0; i < size; i++)
            g.fillRect(i * length, height, length, (int) (multiplier * -numbers[i] - 30));

        if (i != -1 && j != -1) {
            g.setColor(Color.RED);
            g.fillRect(i * length, height, length, (int) (multiplier * -numbers[i] - 30));
            g.fillRect(j * length, height, length, (int) (multiplier * -numbers[j] - 30));
        }

        if (k != -1) {
            g.setColor(Color.BLACK);
            g.fillRect(k * length, height, length, (int) (multiplier * -numbers[k] - 30));
        }

        g.setColor(Color.BLACK);
        if (length > 10) {
            for (int i = 0; i < size; i++)
                g.drawString(String.valueOf(numbers[i]), (i * length + length / 2) - 1, 700);
        }

        g.drawString("Ticks: " + ticks, 40, 40);
        g.drawString("TPS: " + sortingAlgos.getUpsSet(), 40, 60);
        g.drawString("FPS: 120", 40, 80);
        g.drawString("Size: " + size, 40, 100);

        g.drawString("UP/DOWN: Faster/Slower", 300, 40);
        g.drawString("LEFT/RIGHT: Increase/Decrease array", 300, 60);
        g.drawString("R: Randomize", 300, 80);
        g.drawString("O: Repeat array", 300, 100);

        g.drawString("I: Insertion Sort", 600, 40);
        g.drawString("B: Bubble Sort", 600, 60);
        g.drawString("Q: Quick Sort", 600, 80);
        g.drawString("M: Merge Sort (in-place)", 600, 100);
    }

    public void update() {
        if (faster)
            sortingAlgos.setUpsSet(sortingAlgos.getUpsSet() + 10);
        else if (slower)
            sortingAlgos.setUpsSet(sortingAlgos.getUpsSet() - 10);


        if (insertionSortBol)
            insertionSort.iterate();
        else if (bubbleSortBol)
            bubbleSort.iterate();
        else if (quickSortBol)
            quickSort.iterate();
        else if (mergeSort)
            iterateMergeSort();

        if (!sorting) {
            if (lowerSize)
                setSize(length + 1, true);
            else if (upSize)
                setSize(length - 1, false);

            if (old) {
                numbers = oldNumbers.clone();
                ticks = 0;
            }
        } else {
            ticks++;
        }
    }
}


// merge Dead code
//        else if (i == startjMerge){
//            count_size *= 2;
//         i = startiMerge + count_size;
//         j = startjMerge + count_size;
//         startiMerge = i;
//         startjMerge = j;
//        }else if (j >= size-1){
//            count_size *= 2;
//            i = 0;
//            j = count_size;
//        }else if(shiftingForMerge){
//            if (counterForMerge > 0){
//                numbers[i+counterForMerge] = numbers[i+ counterForMerge-1];
//                counterForMerge--;
//            }else {
//                shiftingForMerge = false;
//                numbers[i] = tempForMerge;
//                i++;
//                j++;
//            }
//        }
//        else if (j == i+1 && numbers[i] > numbers[j])
//            exchange(numbers, i,j);
//        else if (numbers[i] < numbers[j]) {
//            i++;
//        } else if(numbers[i] > numbers[j]){
//            tempForMerge = numbers[j];
//            counterForMerge = j - i;
//            shiftingForMerge = true;
//        }