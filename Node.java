import java.io.*;
import java.util.*;

public class Node<T> {
    public T data;
    public Node next;
    public int identifier;

    public Node(T d, Node n, int i){
        this.data = d;
        this.next = n;
        this.identifier = i;
    }
}