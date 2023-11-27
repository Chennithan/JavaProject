package discussion.questions;

import java.util.LinkedList;

public class SinglyLinkedList {

	public static void main(String[] args) {
		
		LinkedList<String> list = new LinkedList<String>();
		
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add(2, "B again");
		
		System.out.println(list.size());
		System.out.println(list);
		
		LinkedList<Integer> listTwo = new LinkedList<Integer>();
		
		listTwo.add(1);
		listTwo.add(2);
		listTwo.add(3);
		listTwo.add(4);
		listTwo.add(5);
		listTwo.add(6);
		listTwo.listIterator(2);
		System.out.println(listTwo);
		
		

	}

}
