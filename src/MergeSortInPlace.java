// AUTHOR: DANIEL RAMOS

public class MergeSortInPlace {
    private final Sorts sorts;
    private int i, j, count_size, left, right, size, temp, counter;
    private int[] numbers;
    private boolean shifting;

    public MergeSortInPlace(int[] numbers, Sorts sorts) {
        this.numbers = numbers;
        this.sorts = sorts;
    }

    public void setAll(int size, int[] numbers) {
        this.numbers = numbers;
        this.size = size;
        count_size = 1;
        i = 0;
        j = 1;
        left = 0;
        right = 1;
        temp = 0;
        counter = 0;
        shifting = false;
    }

    public void iterate() {
        if (count_size > size) {
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
            } else if (numbers[i] > numbers[j]) {
                exchange(numbers, i, j);
            } else {
                i += 2;
                j += 2;
            }
        }
        // IF J ENDS
        else if (j >= right + count_size || j >= size) {
            if (j < size && numbers[i] > numbers[j])
                exchange(numbers, i, j);
            int double_count = count_size * 2;
            i = left + double_count;
            j = right + double_count;
            left = i;
            right = j;
            if (i > size) {
                count_size *= 2;
                i = 0;
                j = count_size;
                left = 0;
                right = j;
            }
        }
        // SHIFTING FOR WHEN num[i] > num[j] happens
        else if (shifting) {
            if (counter > 0) {
                numbers[i + counter] = numbers[i + counter - 1];
                counter--;
            } else {
                shifting = false;
                numbers[i] = temp;
                i++;
                j = Math.min(j + 1, right + count_size - 1);
            }
        }
        // IF LEFT SIDE ENDS
        else if (i == j) {
            int double_count = count_size * 2;
            i = left + double_count;
            j = right + double_count;
            left = i;
            right = j;
        }
        // num[i] < num[j]
        else if (numbers[i] < numbers[j]) {
            i++;
        }
        // num[i] > num[j]
        else if (numbers[i] > numbers[j]) {
            temp = numbers[j];
            counter = j - i;
            shifting = true;
        } else
            j = Math.min(Math.min(j + 1, right + count_size), size);

        sorts.setI(i);
        sorts.setJ(j);
    }

    private void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
