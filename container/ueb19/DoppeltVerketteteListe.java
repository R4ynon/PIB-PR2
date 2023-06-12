package ueb19;

import java.util.*;
import java.lang.UnsupportedOperationException;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.Collection;




public class DoppeltVerketteteListe<E> implements List<E> {
    private static final String METHODE_NOT_SUPPORTET = "Methode not supportet!";
    private class DVLIterator<T> implements Iterator<E> {
        private Node<E> startNode;

        public DVLIterator() {
            this.startNode = getHead();
        }

        public boolean hasNext() {
            if (startNode.getNext() != null) {
                return true;
            }
            return false;
        }

        public E next() {
            E help = startNode.getValue();
            startNode = startNode.getNext();
            return help;
        }
    }
        private class DVLListIterator<T> extends DVLIterator<E> implements ListIterator<E>{
            Node<E> startNode;
            public DVLListIterator(){
                this.startNode= getHead();
            }
            public boolean hasPrevious(){
                if(startNode.getPrev()!=null){
                    return true;
                }
                return false;
            }
            public E previous(){
                E help = startNode.getValue();
                startNode = startNode.getPrev();
                return help;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(E e) {

            }

            @Override
            public void add(E e) {

            }
        }

    private class Node<E>{
        private E value;
        private Node<E> next;
        private Node<E> prev;
        public Node(E value, Node<E> next, Node<E> prev){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public void setValue(E t){

            this.value = t;
        }

        public E getValue(){

            return this.value;
        }

        public Node<E> getNext(){

            return this.next;
        }

        public void setNext(Node<E> next){

            this.next = next;
        }

        public Node<E> getPrev(){

            return this.prev;
        }

        public void setPrev(Node<E> prev){

            this.prev = prev;
        }
    }

    private int size = 0;
    // Kopf der Liste
    private Node<E> head;

    // Schwanz der Liste
    private Node<E> tail;
    public Node<E> getHead(){
        return head;
    }
    @Override
    public int size(){
        int size = 0;
        Node<E> cur = head;
        while (cur != null) {
            size++;
            cur = cur.getNext();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {

        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        boolean found = false;
        if (head != null) {
            Node<E> cur = head;
            while (cur != null && cur.getValue() != o) {
                cur = cur.getNext();
            }
            if (cur != null) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public Iterator<E> iterator() {
        return new DVLIterator<>();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);

    }

    @Override
    public <T> T[] toArray(T[] a) {
        Node<E> anfang = head;
        for(int i = 0; i<a.length;i++){
            try {
                a[i] = (T) anfang.getValue();
            }catch (ArrayStoreException e){
                throw new ArrayStoreException("c");
            }

            anfang = anfang.getNext();
        }
        return a;
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public boolean add(E e) {
        if(e.equals(null)){
            throw new NullPointerException("Test");
        }
        Node<E> node = new Node<E>(e, null, null);
        //node.setValue(e);
        // Ist die Liste noch leer?
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.setPrev(tail);
                tail.setNext(node);
                tail = node;
            }
            size += 1;
            return true;





    }

    @Override
    public boolean remove(Object o) {
        if(head == null){
            return false;
        }
        if(head.getValue().equals(o)){
            Node<E> help = head.getNext();
            head = help;
            help.setPrev(null);
            return true;
        }
        if(tail.getValue().equals(o)){
            Node<E> help = tail.getPrev();
            tail = help;
            help.setNext(null);
            return true;
        }
        Node<E> cur = head;
        while(cur.getNext()!=null){
            if(cur.getValue().equals(o)){
                Node<E> prev = cur.getPrev();
                prev.setNext(cur.getNext());
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        //c = new Iter
        if(c == null){
            return false;
        }
        if(c.isEmpty()){
            return false;
        }
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if(size() <= index || index < 0){
            throw new IndexOutOfBoundsException("rofl");

        }
        Node<E> cur = head;
        for(int i = 0 ; i<index; i++){
            cur = cur.getNext();
        }
        return cur.getValue();
    }

    @Override
    public E set(int index, E element) {
        if(size() <= index || index < 0){
            throw new IndexOutOfBoundsException("rofl");

        }
        Node<E> cur = head;
        for(int i = 0 ; i<index; i++){
            cur = cur.getNext();
        }
        E help1 = cur.getValue();
        cur.setValue(element);
        return help1;

    }

    @Override
    public void add(int index, E element) {
        if(index > size()) {
            throw new IndexOutOfBoundsException("roflcopter");
        }
        Node <E> cur = head;
        for(int i = 0; i<index; i++){
            cur = cur.getNext();
        }
        Node<E> previous = cur.getPrev();
        Node<E> newNode = new Node<>(element, cur, previous);
        if(previous != null) {
            previous.setNext(newNode);
        }
        cur.setPrev(newNode);


    }

    @Override
    public E remove(int index) {
        if(index >= size()){
            throw new IndexOutOfBoundsException("Rolf");
        }
        Node <E> cur = head;
        int indexInWork = 0;
        while (index != indexInWork){
            cur = cur.getNext();
            indexInWork++;
        }
        E value = cur.getValue();
        Node<E> hilfsNode = cur.getPrev();
        hilfsNode.setNext(cur.getNext());
        cur = null;
        return value;
    }

    @Override
    public int indexOf(Object o) {
        if(o.equals(null)) {
            throw new NullPointerException("wdym");
        }
        Node<E> cur = head;
        for(int index = 0; index < size(); index++){
            if(cur.getValue().equals(o)){
                return index;
            }
            cur = cur.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public ListIterator<E> listIterator() {

           return new DVLListIterator<E>();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);

    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public Stream<E> stream() {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }

    @Override
    public Stream<E> parallelStream() {
        throw new UnsupportedOperationException(METHODE_NOT_SUPPORTET);
    }
}
