import java.io.*;
import java.util.*;

public class ExchangeList{
	private MySet<Exchange> exchange_list;

	public ExchangeList(){
		this.exchange_list = new MySet<Exchange>();
	}

	public int size(){
		return this.exchange_list.size;
	}

	public void printList(){

		int i;

		for(i=0;i < (this.exchange_list.size-1);i++){
			System.out.print(this.exchange_list.findIthNode(i).number() + ", ");
		}

		System.out.println(this.exchange_list.findIthNode(i).number());
	}

	public Exchange findChild(int i){
		return this.exchange_list.findIthNode(i);
	}

	public void add(int e, Exchange p){
		Exchange exchange = new Exchange(e,p);
		this.exchange_list.Insert(exchange,e);
	}

	public void addExchange(Exchange e){
		this.exchange_list.Insert(e,e.number());
	}
}