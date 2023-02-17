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
    private final MergeSortInPlace mergeSortInPlace;
    private final ShellSort shellSort;
    private int[] numbers;
    private int[] oldNumbers;
    private int size;
    private int length;
    private int i;
    private int j;
    private int k;
    private int ticks;
    private boolean insertionSortBol, bubbleSortBol, quickSortBol, mergeSortInPlaceBol, shellSortBol, sorting, lowerSize, upSize, faster, slower, old;


    public Sorts(int size, int width, int height, SortingAlgos sortingAlgos) {
        numbers = new int[400];
        oldNumbers = new int[400];
        insertionSort = new InsertionSort(numbers, this);
        bubbleSort = new BubbleSort(numbers, this);
        quickSort = new QuickSort(numbers, this);
        mergeSortInPlace = new MergeSortInPlace(numbers, this);
        shellSort = new ShellSort(numbers, this);
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
        mergeSortInPlaceBol = false;
        shellSortBol = false;
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

        while (width / (double) (length) % 1 != 0) {
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
            mergeSortInPlaceBol = false;
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

    public void setMergeSortInPlaceBol(boolean mergeSortInPlaceBol) {
        if (!sorting) {
            this.mergeSortInPlaceBol = mergeSortInPlaceBol;
            sorting = true;
            mergeSortInPlace.setAll(size, numbers);
        }
    }

    public void setShellSort(boolean shellSortBol) {
        if (!sorting) {
            this.shellSortBol = shellSortBol;
            sorting = true;
            shellSort.setAll(size, numbers);
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

        g.setColor(Color.BLACK);
        if (k != -1)
            g.fillRect(k * length, height, length, (int) (multiplier * -numbers[k] - 30));


        if (length > 10)
            for (int i = 0; i < size; i++)
                g.drawString(String.valueOf(numbers[i]), (i * length + length / 2) - 1, 700);


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
        else if (mergeSortInPlaceBol)
            mergeSortInPlace.iterate();
        else if (shellSortBol)
            shellSort.iterate();

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