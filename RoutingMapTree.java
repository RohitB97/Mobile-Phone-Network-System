import java.io.*;
import java.util.*;

public class RoutingMapTree{

	private Exchange _root;

	public RoutingMapTree(){
		this._root = new Exchange(0);
	}

	public RoutingMapTree(int i){
		this._root = new Exchange(i);
	}

	public void switchOn(MobilePhone a, Exchange b){

		a.switchOn(b);
		
		this._root.addMobilePhone(a,a.number());

		while(!b.isRoot()){
			b.addMobilePhone(a,a.number());
			b = b.parent();
		}
	}

	public void switchOff(MobilePhone a){

		Exchange exchange = this.findPhone(a);

		while(!exchange.isRoot()){
			exchange.deleteMobilePhone(a);
			exchange = exchange.parent();
		}

		this._root.deleteMobilePhone(a);

		a.switchOff();
	}

	public Exchange getExchange(int a){
		
		ArrayList<Exchange> queue = new ArrayList<Exchange>();
		queue.add(this._root);
		
		while(queue.get(0) != null){

			Exchange e = queue.get(0);
			
			if(e.number() == a){
				return e;
			}

			for(int i=0;i<e.numChildren();i++){
				queue.add(e.child(i));
			}

			queue.remove(0);

		}

		return null;
	}

	public Exchange findPhone(MobilePhone m){
		return m.location();
	}

	public Exchange lowestRouter(Exchange a, Exchange b){
		
		if(a == b)
			return a;
		
		else{

			a = a.parent();
			b = b.parent();
			
			while(a != b){
				a = a.parent();
				b = b.parent();
			}

			return a;
		}
	}

	public ExchangeList routeCall(MobilePhone a, MobilePhone b){
		
		Exchange router = this.lowestRouter(a.location(),b.location());
		
		ExchangeList routeList = new ExchangeList();

		Exchange e1 = a.location();
		Exchange e2 = b.location();
		
		while(e1 != router){
			routeList.addExchange(e1);
			e1 = e1.parent();
		}

		routeList.addExchange(e1);

		Stack<Exchange> stack = new Stack<Exchange>();

		while(e2 != router){
			stack.push(e2);
			e2 = e2.parent();
		}

		while(!stack.empty()){
			routeList.addExchange(stack.pop());
		}

		return routeList;
	}

	public void movePhone(MobilePhone a, Exchange b){
		this.switchOff(a);
		this.switchOn(a,b);
	}

	public void performAction(String actionMessage){
		
		String sub_strings[] = actionMessage.split(" ");

		if(sub_strings[0].equals("findPhone") || sub_strings[0].equals("queryFindPhone")){

			System.out.print(actionMessage + " : ");
			
			int a = Integer.parseInt(sub_strings[1]);
			
			try{
				MobilePhone mobile = this._root.residentSet().getMobile(a);
				System.out.println(this.findPhone(mobile).number());
			}

			catch(ErrorException e){
				e.message("Error - No mobile phone with identifier " + a + " is found in the network");
			}
		}

		else if (sub_strings[0].equals("queryMobilePhoneSet")) {

			System.out.print(actionMessage + " : ");
			
			int a = Integer.parseInt(sub_strings[1]);
			
			this.getExchange(a).printMobilePhoneSet();
		}

		else if (sub_strings[0].equals("queryNthChild")) {

			System.out.print(actionMessage + " : ");
			
			int a = Integer.parseInt(sub_strings[1]);
			
			int b = Integer.parseInt(sub_strings[2]);
			
			System.out.println(this.getExchange(a).child(b).number());
		}

		else if (sub_strings[0].equals("lowestRouter") || sub_strings[0].equals("queryLowestRouter")) {

			System.out.print(actionMessage + " : ");
			
			int a = Integer.parseInt(sub_strings[1]);
			
			int b = Integer.parseInt(sub_strings[2]);
			
			Exchange e1 = this.getExchange(a);
			
			Exchange e2 = this.getExchange(b);
			
			System.out.println(this.lowestRouter(e1,e2).number());
		}

		else if (sub_strings[0].equals("findCallPath") || sub_strings[0].equals("queryFindCallPath")) {

			System.out.print(actionMessage + " : ");
			
			int a = Integer.parseInt(sub_strings[1]);
			
			int b = Integer.parseInt(sub_strings[2]);
			
			try{
				MobilePhone mobile1 = this._root.residentSet().getMobile(a);
			
				MobilePhone mobile2 = this._root.residentSet().getMobile(b);

				ExchangeList routeList = this.routeCall(mobile1,mobile2);
			
				routeList.printList();
			}

			catch (ErrorException e){
				e.message("Error - Mobile Phone with identifier " + e.identifier + " is currently switched off");
			}
		}

		else if (sub_strings[0].equals("addExchange")) {
			
			int a = Integer.parseInt(sub_strings[1]);
			
			int b = Integer.parseInt(sub_strings[2]);

			this.getExchange(a).addChild(b);
		}

		else if (sub_strings[0].equals("switchOnMobile")) {
			
			int a = Integer.parseInt(sub_strings[1]);
			
			int b = Integer.parseInt(sub_strings[2]);
			
			MobilePhone mobile = new MobilePhone(a,null);
			
			Exchange exchange = this.getExchange(b);
			
			this.switchOn(mobile,exchange); 
		}

		else if (sub_strings[0].equals("switchOffMobile")) {
			
			int a = Integer.parseInt(sub_strings[1]);

			try{
				MobilePhone mobile = this._root.residentSet().getMobile(a);
			
				this.switchOff(mobile);
			}

			catch(ErrorException e){
				e.message("Error - No mobile phone with identifier " + a + " is found in the  network");
			}
		}

		else if (sub_strings[0].equals("movePhone")) {
			
			int a = Integer.parseInt(sub_strings[1]);
			
			int b = Integer.parseInt(sub_strings[2]);

			try{
				MobilePhone mobile = this._root.residentSet().getMobile(a);
			
				Exchange exchange = this.getExchange(b);
			
				this.movePhone(mobile,exchange);
			}

			catch(ErrorException e){
				e.message("Error - Mobile phone with identifier " + a + " is currently switched off");
			}
		}

		else{
			System.out.print(actionMessage + " : ");
			System.out.println("Error - Action is not available");
		}
	}
}