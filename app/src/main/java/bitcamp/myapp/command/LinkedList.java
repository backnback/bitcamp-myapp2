package bitcamp.myapp.command;

public class LinkedList {

  Node first;
  Node last;
  int size;


  public void append(Object value) {
    Node newNode = new Node(value);

    if (first == null) {
      first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }
    size++;
  }

  public Object getValue(int index) {
    if (index < 0 || index >= size) {
      return null;
    }

    Node cursor = first;
    int currentIndex = 0;

    while (cursor != null) {
      if (currentIndex == index) {
        return cursor.value;
      }
      cursor = cursor.next;
      currentIndex++;
    }
    return null;
  }

  public Object delete(int index) {

  }

  public int index(Object value) {

  }

  public Object[] getArray() {
    Object[] arr = new Object[size];

    Node cursor = first;
    for (int i = 0; i < size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

}
