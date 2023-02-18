// AUTHOR: DANIEL RAMOS

public class ShellSort {
    private final Sorts sorts;
    private int i, j, h, size;
    private int[] numbers;

    public ShellSort(int[] numbers, Sorts sorts) {
        this.sorts = sorts;
        this.numbers = numbers;
    }

    public void setAll(int size, int[] numbers) {
        h = 1;
        this.size = size;
        this.numbers = numbers;
        while (h < size / 3) h = 3 * h + 1;
        i = h;
        j = i;
    }

    public void iterate() {
        if (h == 0) {
            i = -1;
            j = -1;
            sorts.setSorting(false);
            return;
        } else if (i >= size) {
            h /= 3;
            i = h;
            j = h;
        } else if (j < h) {
            i++;
            j = i;
        } else if (numbers[j] < numbers[j - h]) {
            exchange(numbers, j, j - h);
            j -= h;
        } else {
            i++;
            j = i;
        }
        sorts.setI(i);
        sorts.setJ(j);
    }


    private void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
