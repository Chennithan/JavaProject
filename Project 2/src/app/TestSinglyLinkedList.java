package app;

public class TestSinglyLinkedList {

    public static void main(String[] args) {
        // Test the default constructor
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        System.out.println("Created new list. Is it empty? " + list.empty());

        // Test insertion
        list.insert(5);
        list.insert(3);
        list.insert(7);
        System.out.println("\nInserted 3 elements (3, 5, 7).");
        list.printList();

        // Test size
        System.out.println("\nList size: " + list.size());

        // Test front and back
        System.out.println("First element: " + list.front());
        System.out.println("Last element: " + list.back());

        // Test popping front and back
        list.pop_front();
        System.out.println("\nPopped front element.");
        list.printList();

        list.pop_back();
        System.out.println("\nPopped back element.");
        list.printList();

        // Test reverse
        list.insert(2);
        list.insert(6);
        System.out.println("\nInserted 2 more elements (2, 6).");
        list.printList();

        list.reverse();
        System.out.println("\nReversed list.");
        list.printList();

        // Test copy constructor and merge
        SinglyLinkedList<Integer> newList = new SinglyLinkedList<>(list);
        System.out.println("\nCreated new list from existing list.");
        newList.printList();

        SinglyLinkedList<Integer> mergeList = new SinglyLinkedList<>();
        mergeList.insert(4);
        mergeList.insert(1);
        System.out.println("\nCreated another list and inserted 2 elements (1, 4).");
        mergeList.printList();

        newList.merge(mergeList);
        System.out.println("\nMerged new list with another list.");
        newList.printList();
    }
}
