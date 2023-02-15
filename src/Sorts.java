import java.awt.*;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

// THIS IS WHERE EVERYTHING HAPPENS
// AUTHOR: DANIEL RAMOS
// DATE: 14/02/2023

public class Sorts {
    private int[] numbers;
    private int[] oldNumbers;
    private final int height;
    private final int width;
    private int size;
    private int length;
    private int i;
    private int j;
    private int k;
    private int lowQS;
    private int highQS;
    private int pivotQS;
    private int ticks;
    private boolean insertionSort, bubbleSort, quickSort, partitionQS, sorting, lowerSize, upSize, faster, slower, old;
    private final Stack<Integer> stackForQuickSort;
    private final SortingAlgos sortingAlgos;


    public Sorts(int size, int width, int height, SortingAlgos sortingAlgos) {
        numbers = new int[400];
        oldNumbers = new int[400];
        this.sortingAlgos = sortingAlgos;
        this.size = size;
        this.height = height;
        this.width = width;
        length = width / size;
        lowQS = 0;
        highQS = size - 1;
        stackForQuickSort = new Stack<>();
        randomizeArraySame();
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
        insertionSort = false;
        bubbleSort = false;
        quickSort = false;
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

    public void setOld (boolean old){
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

    public void setInsertionSort(boolean insertionSort) {
        if (!sorting) {
            this.insertionSort = insertionSort;
            sorting = true;
            i = 1;
            j = 1;
        }
    }

    public void setBubbleSort(boolean bubbleSort) {
        if (!sorting) {
            this.bubbleSort = bubbleSort;
            sorting = true;
            i = 0;
            j = 0;
        }
    }

    public void setQuickSort(boolean quickSort) {
        if (!sorting) {
            this.quickSort = quickSort;
            sorting = true;
            lowQS = 0;
            highQS = size - 1;
            k = highQS;
            i = lowQS;
            j = highQS;
            stackForQuickSort.push(lowQS);
            stackForQuickSort.push(highQS);
        }
    }

    //FAZER CLASSES Á PARTE TESTE

    // INSERTION SORT
    private void iterateInsertionSort() {
        ticks++;
        if (j == size) {
            i = -1;
            j = -1;
            insertionSort = false;
            sorting = false;
        } else if (j == 0) {
            resetInsertionSort();
        }
        else if (numbers[j] < numbers[j - 1]) {
            exchange(numbers, j, j - 1);
        }
        else {
            resetInsertionSort();
        }
        j--;
    }

    private void resetInsertionSort() {
        i++;
        j = i;
    }

    // BUBBLE SORT
    private void iterateBubbleSort() {
        ticks++;
        if (i >= size - 1) {
            i = -1;
            j = -1;
            bubbleSort = false;
            sorting = false;
        } else if (j >= size - i - 1)
            resetBubbleSort();
        else if (numbers[j] > numbers[j + 1])
            exchange(numbers, j, j + 1);
        else
            j++;
    }

    private void resetBubbleSort() {
        i++;
        j = 0;
    }

    // QUICK SORT
    private void iterateQuickSort() {
        ticks++;
        if (partitionQS) {
            if (j > highQS-1) {
                exchange(numbers, i + 1, highQS);
                pivotQS = i + 1;
                partitionQS = false;

                if (pivotQS + 1 < highQS) {
                    stackForQuickSort.push(pivotQS + 1);
                    stackForQuickSort.push(highQS);
                }
                if (pivotQS - 1 > lowQS) {
                    stackForQuickSort.push(lowQS);
                    stackForQuickSort.push(pivotQS - 1);
                }
                return;
            }
            if (numbers[j] < pivotQS) {
                i++;
                exchange(numbers, i, j);
            }
            j++;
        } else if (!stackForQuickSort.isEmpty()) {
            highQS = stackForQuickSort.pop();
            lowQS = stackForQuickSort.pop();
            pivotQS = numbers[highQS];
            k = highQS;
            i = lowQS - 1;
            j = lowQS;
            partitionQS = true;
        } else {
            i = -1;
            j = -1;
            k = -1;
            quickSort = false;
            sorting = false;
        }
    }


    // RENDER AND UPDATE
    public void render(Graphics g) {
        double multiplier = length == 2 ? 1.6 : length - 1;

        g.setColor(Color.GREEN);
        for (int i = 0; i < size; i++)
            g.fillRect(i * length, height, length, (int) (multiplier * -numbers[i] - 30));


        g.setColor(Color.BLACK);
        if (length > 10)
            for (int i = 0; i < size; i++)
                g.drawString(String.valueOf(numbers[i]), (i * length + length / 2) - 1, 700);

        if (i != -1 && j != -1) {
            g.setColor(Color.RED);
            g.fillRect(i * length, height, length, (int) (multiplier * -numbers[i] - 30));
            g.fillRect(j * length, height, length, (int) (multiplier * -numbers[j] - 30));
        }

        if (k != -1) {
            g.setColor(Color.BLACK);
            g.fillRect(k * length, height, length, (int) (multiplier * -numbers[k] - 30));
        }

        g.drawString("Ticks: " + ticks, 40, 40);
        g.drawString("UPS: " + sortingAlgos.getUpsSet(), 40, 60);
        g.drawString("FPS: 120", 40, 80);
        g.drawString("Size: " + size, 40, 100);

        g.drawString("UP/DOWN: Faster/Slower", 300, 40);
        g.drawString("LEFT/RIGHT: Increase/Decrease array", 300, 60);
        g.drawString("R: Randomize", 300, 80);
        g.drawString("O: Repeat array", 300, 100);

        g.drawString("I: Insertion Sort", 600, 40);
        g.drawString("B: Bubble Sort", 600, 60);
        g.drawString("Q: Quick Sort", 600, 80);
    }

    public void update() {
        if (faster)
            sortingAlgos.setUpsSet(sortingAlgos.getUpsSet() + 10);
        else if (slower)
            sortingAlgos.setUpsSet(sortingAlgos.getUpsSet() - 10);


        if (insertionSort)
            iterateInsertionSort();
        else if (bubbleSort)
            iterateBubbleSort();
        else if (quickSort)
            iterateQuickSort();

        if (!sorting) {
            if (lowerSize)
                setSize(length + 1, true);
            else if (upSize)
                setSize(length - 1, false);

            if (old) {
                numbers = oldNumbers.clone();
                ticks = 0;
            }
        }
    }
}
