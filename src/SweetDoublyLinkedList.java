import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.lang.reflect.Array;

/**
 * A custom implementation of the List interface using a Doubly-Linked List
 * for storing Sweet objects, as required by Lab 6.
 */
public class SweetDoublyLinkedList implements List<Sweet> {

    private static class Node {
        Sweet item;
        Node next;
        Node prev;

        Node(Node prev, Sweet element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head;
    private Node tail;
    private int size = 0;

    public SweetDoublyLinkedList() {
    }

    public SweetDoublyLinkedList(Sweet sweet) {
        this.add(sweet);
    }

    public SweetDoublyLinkedList(Collection<? extends Sweet> c) {
        this.addAll(c);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean add(Sweet sweet) {
        linkLast(sweet);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node x = head; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = head; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Sweet> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        if (c.isEmpty()) {
            return false;
        }
        boolean modified = false;
        for (Sweet sweet : c) {
            if (add(sweet)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Sweet> c) {
        checkPositionIndex(index);
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        if (c.isEmpty()) {
            return false;
        }
        boolean modified = false;
        for (Sweet sweet : c) {
            add(index++, sweet);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        boolean modified = false;
        Iterator<Sweet> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        boolean modified = false;
        Iterator<Sweet> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        for (Node x = head; x != null;) {
            Node next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public Sweet get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public Sweet set(int index, Sweet element) {
        checkElementIndex(index);
        Node x = node(index);
        Sweet oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    @Override
    public void add(int index, Sweet element) {
        checkPositionIndex(index);
        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    @Override
    public Sweet remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node x = head; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node x = head; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        if (o == null) {
            for (Node x = tail; x != null; x = x.prev) {
                if (x.item == null)
                    return index;
                index--;
            }
        } else {
            for (Node x = tail; x != null; x = x.prev) {
                if (o.equals(x.item))
                    return index;
                index--;
            }
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node x = head; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node x = head; x != null; x = x.next) {
            result[i++] = x.item;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public Iterator<Sweet> iterator() {
        return new SweetIterator();
    }

    @Override
    public ListIterator<Sweet> listIterator() {
        return new SweetListIterator(0);
    }

    @Override
    public ListIterator<Sweet> listIterator(int index) {
        checkPositionIndex(index);
        return new SweetListIterator(index);
    }

    /**
     * Returns a new list containing elements from the specified range.
     * NOTE: This is a simplified implementation. It returns a *copy*
     * of the sublist, not a "view" as specified by the List interface,
     * as implementing a full modifiable view is extremely complex.
     *
     * @param fromIndex low endpoint (inclusive)
     * @param toIndex   high endpoint (exclusive)
     * @return a new SweetDoublyLinkedList containing a copy of the elements
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     */
    @Override
    public List<Sweet> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }

        SweetDoublyLinkedList sub = new SweetDoublyLinkedList();
        Node current = node(fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            sub.add(current.item);
            current = current.next;
        }

        return sub;
    }

    void linkLast(Sweet e) {
        final Node l = tail;
        final Node newNode = new Node(l, e, null);
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;
    }

    void linkBefore(Sweet e, Node succ) {
        final Node pred = succ.prev;
        final Node newNode = new Node(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            head = newNode;
        else
            pred.next = newNode;
        size++;
    }

    Sweet unlink(Node x) {
        final Sweet element = x.item;
        final Node next = x.next;
        final Node prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    Node node(int index) {
        if (index < (size >> 1)) {
            Node x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private class SweetIterator implements Iterator<Sweet> {
        private Node lastReturned;
        private Node next;
        private int nextIndex;

        SweetIterator() {
            this.next = head;
            this.nextIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public Sweet next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException("next() must be called before remove()");
            }
            Node lastNext = lastReturned.next;
            SweetDoublyLinkedList.this.unlink(lastReturned);
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            lastReturned = null;
        }
    }

    private class SweetListIterator implements ListIterator<Sweet> {
        private Node lastReturned;
        private Node next;
        private int nextIndex;

        SweetListIterator(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public Sweet next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public Sweet previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastReturned = next = (next == null) ? tail : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            Node lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            lastReturned = null;
        }

        @Override
        public void set(Sweet sweet) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            lastReturned.item = sweet;
        }

        @Override
        public void add(Sweet sweet) {
            lastReturned = null;
            if (next == null) {
                linkLast(sweet);
            } else {
                linkBefore(sweet, next);
            }
            nextIndex++;
        }
    }
}