// AUTHOR: DANIEL RAMOS

import java.util.Stack;

public class QuickSort {
    private final Sorts sorts;
    private final Stack<Integer> stackForQuickSort;
    private int i, j, k, low, high, pivot;
    private int[] numbers;
    private boolean partition;

    public QuickSort(int[] numbers, Sorts sorts) {
        this.sorts = sorts;
        this.numbers = numbers;
        stackForQuickSort = new Stack<>();
    }

    public void setAll(int size, int[] numbers) {
        this.numbers = numbers;
        i = 0;
        j = size - 1;
        k = j;
        low = 0;
        high = j;
        pivot = k;
        partition = false;
        stackForQuickSort.clear();
        stackForQuickSort.push(low);
        stackForQuickSort.push(high);
    }

    public void iterate() {
        if (partition) {
            if (j > high - 1) {
                exchange(numbers, i + 1, high);
                pivot = i + 1;
                k = pivot;
                partition = false;

                if (pivot + 1 < high) {
                    stackForQuickSort.push(pivot + 1);
                    stackForQuickSort.push(high);
                }
                if (pivot - 1 > low) {
                    stackForQuickSort.push(low);
                    stackForQuickSort.push(pivot - 1);
                }
                return;
            }
            if (numbers[j] < pivot) {
                i++;
                exchange(numbers, i, j);
            }
            j++;
        } else if (!stackForQuickSort.isEmpty()) {
            high = stackForQuickSort.pop();
            low = stackForQuickSort.pop();
            pivot = numbers[high];
            k = high;
            i = low - 1;
            j = low;
            partition = true;
        } else {
            i = -1;
            j = -1;
            k = -1;
            sorts.setSorting(false);
        }

        sorts.setI(i);
        sorts.setJ(j);
        sorts.setK(k);
    }

    private void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
