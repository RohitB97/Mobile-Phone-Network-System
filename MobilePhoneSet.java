import java.io.*;
import java.util.*;

public class MobilePhoneSet{

	private MySet<MobilePhone> resident_set;

	public MobilePhoneSet(){
		this.resident_set = new MySet<MobilePhone>();
	}

	public int size(){
		return this.resident_set.size;
	}

	public void printSet(){

		int i;

		for(i=0;i < (this.resident_set.size-1);i++){
			System.out.print(this.resident_set.findIthNode(i).number() + ", ");
		}

		System.out.println(this.resident_set.findIthNode(i).number());
	}

	public void addMobilePhone(MobilePhone m, int i){
		this.resident_set.Insert(m,i);
	}

	public void deleteMobilePhone(MobilePhone m){
		this.resident_set.Delete(m);
	}

	public MobilePhone getMobile(int m) throws ErrorException{
		return this.resident_set.getNode(m);
	}
}