package app;

import java.util.LinkedList;

public class MazeStack<T> {
	
	private LinkedList<T> list = new LinkedList<>();

    public void push(T element) {
        list.addFirst(element);
    }

    public T pop() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    public int size() {
    	return list.size();
    }

    public T front() {
        return list.getFirst();
        
    }
}
