package DynamicArray;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {
    private T[] array;
    private int capacity = 0;
    private int size = 0;

    // If no input value
    public DynamicArray() {
        this.capacity = 10;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity can't be negative: " + capacity);

        this.size = 0;
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        return array[index];
    }

    public void set(int index, T value) {
        array[index] = value;
    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        this.size = 0;
    }

    public void add(T element) {
        if (size >= capacity - 1) {
            if (capacity == 0) {
                capacity += 1;
            } else {
                capacity *= 2;
            }
            T[] newArray = (T[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size++] = element;
    }


    public void removeAt(int removeIndex) {
        if (removeIndex >= size || removeIndex < 0) throw new IndexOutOfBoundsException();
        T[] newArray = (T[]) new Object[size - 1];

        for (int oldArrIndex = 0, newArrIndex = 0; oldArrIndex < size; oldArrIndex++, newArrIndex++) {
            if (oldArrIndex == removeIndex) newArrIndex--;
            else newArray[newArrIndex] = array[oldArrIndex];
        }

        array = newArray;
        capacity = --size;
        size--;
    }

    public void remove(Object object) {
        int removeIndex = indexOf(object);
        removeAt(removeIndex);
    }

    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object == null) {
                if (array[i] == null) return i;
            } else {
                if (object.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contain(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        else {
            StringBuilder sb = new StringBuilder(size);
            sb.append("[");
            for (int i = 0; i < size - 1; i++) {
                sb.append(array[i]).append(",");
            }
            sb.append(array[size - 1]).append("]");

            return (sb.toString());
        }
    }

}
