import java.io.*;
import java.util.*;

public class ErrorException extends Exception{

	public int identifier;

	public ErrorException(int i){
		this.identifier = i;
	}
	
	public void message(String s){
		System.out.println(s);
	}
}