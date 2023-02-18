// AUTHOR: DANIEL RAMOS

public class MergeSort {
    private final Sorts sorts;
    private final int[] aux;
    private int i, j, count_size, left, right, size, auxCounter;
    private int[] numbers;
    private boolean auxToNumbers, reset;

    public MergeSort(int[] numbers, Sorts sorts) {
        this.numbers = numbers;
        this.sorts = sorts;
        aux = new int[401];
    }

    public void setAll(int size, int[] numbers) {
        this.numbers = numbers;
        this.size = size;
        count_size = 1;
        i = 0;
        j = 1;
        left = 0;
        right = 1;
        auxCounter = 0;
    }

    public void iterate() {
        if (count_size >= size) {
            i = -1;
            j = -1;
            sorts.setSorting(false);
        }
        //OPTIMIZED FOR FIRST
        else if (count_size == 1) {
            if (j >= size) {
                count_size *= 2;
                i = 0;
                j = count_size;
                left = 0;
                right = count_size;
            } else if (numbers[i] > numbers[j])
                exchange(numbers, i, j);
            else {
                i += 2;
                j += 2;
            }
        }
        // GRABBING FROM THE AUX AND PUTTING ON ARRAY
        else if (auxToNumbers) {
            if (j <= left) {
                reset = true;
                auxToNumbers = false;
            }
            numbers[j--] = aux[auxCounter--];
        }
        // GOING FORWARD, OR RESETTING AND INCREASING GROUP-SIZE
        else if (reset) {
            int double_count = count_size * 2;
            i = left + double_count;
            j = Math.min(right + double_count, size);
            left = i;
            right = j;
            auxCounter = 0;
            reset = false;
            if (i >= size || j >= size) {
                count_size *= 2;
                i = 0;
                j = count_size;
                left = 0;
                right = j;
            }
        }
        // IF COMPARING
        else if (auxCounter < (count_size * 2)) {
            if (i >= Math.min(left + count_size, size))
                aux[auxCounter++] = numbers[j++];
            else if (j >= Math.min(right + count_size, size))
                aux[auxCounter++] = numbers[i++];
            else if (numbers[i] > numbers[j])
                aux[auxCounter++] = numbers[j++];
            else
                aux[auxCounter++] = numbers[i++];

            if (auxCounter >= (count_size * 2) || (j == size && i >= right)) {
                auxToNumbers = true;
                auxCounter--;
                j--;
            }
        } else if (auxCounter >= (count_size * 2)) {
            auxToNumbers = true;
            auxCounter--;
            j--;
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
