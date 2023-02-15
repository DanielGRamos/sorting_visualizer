// AUTHOR: DANIEL RAMOS

public class InsertionSort {
    private final Sorts sorts;
    private int i, j, size;
    private int[] numbers;

    public InsertionSort(int i, int j, int[] numbers, Sorts sorts) {
        this.i = i;
        this.j = j;
        this.sorts = sorts;
        this.numbers = numbers;
    }

    public void setAll(int size, int[] numbers) {
        i = 1;
        j = 1;
        this.size = size;
        this.numbers = numbers;
    }

    public void iterate() {
        if (j == size) {
            i = -1;
            j = -1;
            sorts.setSorting(false);
            return;
        } else if (j == 0) {
            resetInsertionSort();
        } else if (numbers[j] < numbers[j - 1]) {
            exchange(numbers, j, j - 1);
        } else {
            resetInsertionSort();
        }
        j--;

        sorts.setI(i);
        sorts.setJ(j);
    }

    private void resetInsertionSort() {
        i++;
        j = i;
    }


    private void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
