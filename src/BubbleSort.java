// AUTHOR: DANIEL RAMOS

public class BubbleSort {
    private final Sorts sorts;
    private int i, j, size;
    private int[] numbers;

    public BubbleSort(int[] numbers, Sorts sorts) {
        this.sorts = sorts;
        this.numbers = numbers;
    }

    public void setAll(int size, int[] numbers) {
        i = 0;
        j = 1;
        this.numbers = numbers;
        this.size = size;
    }

    public void iterate() {
        if (i >= size - 1) {
            i = -1;
            j = -1;
            sorts.setSorting(false);
        } else if (j >= size - i - 1)
            resetBubbleSort();
        else if (numbers[j] > numbers[j + 1])
            exchange(numbers, j, j + 1);
        else
            j++;

        sorts.setI(size - i);
        sorts.setJ(j);
    }

    private void resetBubbleSort() {
        i++;
        j = 0;
    }

    private void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
