import java.io.*;
import java.util.*;

public class MobilePhone{

	private boolean status;
	private int phone_id;
	private Exchange exchange;
	
	public MobilePhone(int number, Exchange exchange){
		this.status = false;
		this.phone_id = number;
		this.exchange = exchange;
	}

	public int number(){
		return this.phone_id;
	}

	public boolean status(){
		return status;
	}

	public void switchOn(Exchange exchange){
		this.status = true;
		this.exchange = exchange;
	}

	public void switchOff(){
		this.status = false;
		this.exchange = null;
	}

	public Exchange location(){
		return this.exchange;
	}
}