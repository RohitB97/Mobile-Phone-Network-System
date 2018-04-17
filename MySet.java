import java.io.*;
import java.util.*;

public class MySet<T>{

	private Node<T> head;
	public int size;

	public MySet(){
		this.head = null;
		this.size = 0;
	}

	public boolean isEmpty(){
		
		if(this.size == 0)
			return true;
		else
			return false;
	}

	public boolean isMember(T obj){

		Node<T> node = this.head;

		while(node != null){
			if(node.data == obj)
				return true;
			else
				node = node.next;
		}

		return false;
	}

	public void Insert(T obj, int i){

		Node<T> new_node = new Node<T>(obj,null,i);

		if(this.head == null)
			this.head = new_node;

		else{
			
			Node<T> node = this.head;
			
			while(node.next != null){
				node = node.next;
			}

			node.next = new_node;
		}

		this.size++;
	}

	public void Delete(T obj){
		
		Node<T> node = this.head;

		if(node.data == obj){
			this.head = node.next;
			this.size--;
		}

		while(node.next != null){
			
			if(node.next.data == obj){
				node.next = node.next.next;
				this.size--;
				break;
			}
			
			else
				node = node.next;
		}
	}

	public T findIthNode(int i){
		
		Node<T> node = this.head;
		int count = 0;

		while(node != null){
			if(count == i)
				return node.data;
			else
				node = node.next;
			count++;
		}

		return null;

	}

	public T getNode(int i) throws ErrorException{
		
		Node<T> node = this.head;

		while(node != null){
			if(node.identifier == i)
				return node.data;
			else
				node = node.next;
		}

		throw new ErrorException(i);

	}
}