package bitcamp.myapp.util;

import java.util.Arrays;

public class ArrayList extends AbstractList {

  private static final int MAX_SIZE = 100;
  private Object[] list = new Object[MAX_SIZE];


  @Override
  public void add(Object obj) {
    list[size++] = obj;
    if (size == list.length) {
      int oldSize = list.length;
      int newSize = oldSize + (oldSize >> 1);
      list = Arrays.copyOf(list, newSize);
    }
    list[size++] = obj;
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[size];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = list[i];
    }
    return arr;
  }

  @Override
  public int indexOf(Object obj) {
    for (int i = 0; i < size; i++) {
      if (list[i].equals(obj)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public Object remove(int index) {
    if (index < 0 || index >= size) {
      return null;
    }

    Object deletedObj = list[index];
    for (int i = index + 1; i < size; i++) {
      list[i - 1] = list[i];
    }
    list[--size] = null;
    return deletedObj;
  }


  @Override
  public Object get(int index) {
    if (index < 0 || index >= size) {
      return null;
    }

    return list[index];
  }

  public boolean contains(Object obj) {
    return indexOf(obj) != -1;
  }

}
