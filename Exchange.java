import java.io.*;
import java.util.*;

public class Exchange{

	private MobilePhoneSet resident_set = new MobilePhoneSet();
	private int exchange_id;
	private Exchange parent;
	private ExchangeList child_list = new ExchangeList();

	public Exchange(int number){
		this.exchange_id = number;
		this.parent = null;
	}

	public Exchange(int number, Exchange parent){
		this.exchange_id = number;
		this.parent = parent;
	}

	public int number(){
		return this.exchange_id;
	}

	public Exchange parent(){
		return this.parent;
	}

	public void addChild(int child){
		this.child_list.add(child, this);
	}

	public int numChildren(){
		return this.child_list.size();
	}

	public Exchange child(int i){
		return this.child_list.findChild(i);
	}

	public boolean isRoot(){
		if(this.exchange_id == 0)
			return true;
		else
			return false;
	}

	public void addMobilePhone(MobilePhone m, int i){
		this.resident_set.addMobilePhone(m,i);
	}

	public void deleteMobilePhone(MobilePhone m){
		this.resident_set.deleteMobilePhone(m);
	}

	public MobilePhoneSet residentSet() throws ErrorException{
		return this.resident_set;
	}

	public ExchangeList childList(){
		return this.child_list;
	}

	public void printMobilePhoneSet(){
		this.resident_set.printSet();
	}
}