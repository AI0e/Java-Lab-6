import java.util.ArrayList;
import java.util.List;

/**
 * Main executable class for Laboratory Work #6.
 * This class demonstrates the custom collection 'SweetDoublyLinkedList'.
 *
 * It will:
 * 1. Demonstrate all 3 required constructors.
 * 2. Demonstrate core methods (add, get, remove, set, iterator).
 * 3. Demonstrate bulk operations (addAll, removeAll).
 */
public class Lab6 {

    public static void main(String[] args) {

        System.out.println("--- Lab 6: Custom Doubly-Linked List ---");

        try {
            // --- 1. Demonstrate Constructor 1 (empty) ---
            System.out.println("\n--- 1. Testing Constructor 1 (empty) ---");
            List<Sweet> list1 = new SweetDoublyLinkedList();
            list1.add(new Candy("Snickers", 55, 30.0, 0.30, "Caramel"));
            System.out.println("List 1 size: " + list1.size());
            System.out.println(list1.get(0));

            // --- 2. Demonstrate Constructor 2 (single element) ---
            System.out.println("\n--- 2. Testing Constructor 2 (single element) ---");
            Sweet chupa = new Lollipop("Chupa Chups", 12, 10.0, "Cherry");
            List<Sweet> list2 = new SweetDoublyLinkedList(chupa);
            System.out.println("List 2 size: " + list2.size());
            System.out.println(list2.get(0));

            // --- 3. Demonstrate Constructor 3 (from collection) ---
            System.out.println("\n--- 3. Testing Constructor 3 (from collection) ---");
            List<Sweet> standardList = new ArrayList<>();
            standardList.add(new ChocolateBar("Dark Crown", 90, 30.0, 75));
            standardList.add(new Candy("Milky Way", 52, 35.0, 0.35, "Nougat"));

            List<Sweet> list3 = new SweetDoublyLinkedList(standardList);
            System.out.println("List 3 size: " + list3.size());

            // --- 4. Demonstrate core methods (add, remove, for-each) ---
            System.out.println("\n--- 4. Testing core methods ---");
            System.out.println("Original list3:");
            printList(list3);

            // Test add(index)
            list3.add(1, new Lollipop("Lollipoop", 10, 8.0, "Apple"));
            System.out.println("\nAfter add(1, ...):");
            printList(list3);

            // Test remove(index)
            Sweet removed = list3.remove(0);
            System.out.println("\nRemoved element at [0]: " + removed.getName());
            printList(list3);

            // Test set(index)
            Sweet old = list3.set(0, new Candy("Twix", 50, 28.0, 0.33, "Biscuit"));
            System.out.println("\nReplaced '" + old.getName() + "' with 'Twix':");
            printList(list3);

            // Test remove(Object)
            list3.remove(chupa);
            list3.remove(list3.get(1));
            System.out.println("\nAfter removing 'Milky Way' object:");
            printList(list3);

            // Test clear()
            list3.clear();
            System.out.println("\nAfter clear():");
            System.out.println("Is list empty? " + list3.isEmpty());

        } catch (Exception e) {
            System.err.println("\n--- AN ERROR OCCURRED ---");
            e.printStackTrace();
        }
    }

    /**
     * Helper method to print any List<Sweet> using a for-each loop
     * (which proves our iterator() works).
     *
     * @param list The list to print.
     */
    private static void printList(List<Sweet> list) {
        int index = 0;
        for (Sweet s : list) {
            System.out.printf("  [%d] %s\n", index++, s);
        }
    }
}