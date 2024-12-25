package bitcamp.myapp.util;

public interface List {

  public abstract void add(Object value);

  public abstract Object remove(int index);

  public abstract Object get(int index);

  public abstract int indexOf(Object value);

  public abstract Object[] toArray();

  public abstract int size();

}
