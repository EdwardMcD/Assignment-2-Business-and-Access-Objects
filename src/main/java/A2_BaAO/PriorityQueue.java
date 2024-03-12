/*************************************************
 File: PriorityQueue.java
 By: Edward McDonald
 Date: March 11th, 2024
 Compile: -
 Description: Priority queue data structure for Assignment 2
 *************************************************/

package A2_BaAO;

public class PriorityQueue<T extends Comparable<T>> {
    // Define instance variables
    private Node<T> head;
    private int size;

    // Define a private inner class for the node
    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Constructor
    public PriorityQueue() {
        this.head = null;
        this.size = 0;
    }

    // Method to add an element to the priority queue
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null || element.compareTo(head.data) <= 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null && element.compareTo(current.next.data) > 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    // Method to remove and return the highest priority element from the priority queue
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        T removedData = head.data;
        head = head.next;
        size--;
        return removedData;
    }

    // Method to check if the priority queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to return the size of the priority queue
    public int size() {
        return size;
    }
}