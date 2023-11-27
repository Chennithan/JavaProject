package app;

public class SinglyLinkedList<T extends Comparable<T>> {
	
	// List node constructor
	private class Node {
		T data;
		Node next;

		Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	private Node head;
	private Node tail;
	private int size;

	// Default constructor
	public SinglyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	} // O(1)

	// Copy Constructor
	public SinglyLinkedList(SinglyLinkedList<T> aList) {
		for (Node current = aList.head; current != null; current = current.next) {
			this.insert(current.data);
		}
	} // O(1)

	// Access the first element
	public T front() {
		if (empty())
			throw new RuntimeException("List is empty");
		return head.data;
	} // O(1)

	// Access last element
	public T back() {
		if (empty())
			throw new RuntimeException("List is empty");
		return tail.data;
	} // O(1)

	// Insert new element
	public void insert(T val) {
		Node newNode = new Node(val);

		if (empty()) {
			head = newNode;
			tail = newNode;
		} else if (head.data.compareTo(val) >= 0) {
			newNode.next = head;
			head = newNode;
		} else {
			Node current = head;
			while (current.next != null && current.next.data.compareTo(val) < 0) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
			if (current == tail) {
				tail = newNode;
			}
		}
		size++;
	} // O(n)

	// Remove front of the list
	public void pop_front() {
		if (empty())
			throw new RuntimeException("List is empty");
		head = head.next;
		if (head == null) {
			tail = null;
		}

		size--;
	} //O(1)

	// Remove back of the list
	public void pop_back() {
		if (empty())
			throw new RuntimeException("List is empty");

		if (head == tail) {
			head = null;
			tail = null;
		} else {
			Node current = head;
			while (current.next != tail) {
				current = current.next;
			}
			current.next = null;
			tail = current;
		}
		size--;
	} 

	// Determine if empty
	public boolean empty() {
		return size == 0;
	}

	// Return number of elements
	public int size() {
		return size;
	}

	// Reverse order of elements
	public void reverse() {
		Node prev = null;
		Node current = head;
		tail = head;
		Node next;

		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		head = prev;
	}

	// Merge with another ordered list
	public void merge(SinglyLinkedList<T> aList) {
		Node current = aList.head;
		while (current != null) {
			this.insert(current.data);
			current = current.next;
		}
	}

	// Print list contents
	public void printList() {
		Node current = head;
		while (current != null) {
			System.out.print(current.data + " -> ");
			current = current.next;
		}
		System.out.println("END");
	}

}
