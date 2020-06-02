package ExternalFunction;

import java.sql.SQLException;
import java.util.Scanner;
import Public.DB_Operation;

public class ExternalFunction_main {
	public static String Input(int closesc) {
		Scanner sc = new Scanner(System.in);
		String result = sc.nextLine();
		if(closesc == 1) sc.close();
		return result;
	}
	public static int selectfunctions() {
		System.out.println("Please select the following functions£º");
		System.out.println("Input 1: Check abnormal behavior");
		System.out.println("Input 2: Delete abnormal behavior");
		System.out.println("Input 3: Check abnormal event");
		System.out.println("Input 4: Delete abnormal event");
		String option = Input(0);
		if(option.equals("1")) return 1;
		else if(option.equals("2")) return 2;
		else if(option.equals("3")) return 3;
		else if(option.equals("4")) return 4;
		else return 0;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int option = selectfunctions();
		if(option==1) {
			DB_Operation.Connect("abbehavior");
			System.out.println("Please input the abnormal behavior's ID:"); 
			String AbBehaviorID = Input(1);
			int EventCode = AbBehavior.EventCodeCheck(AbBehaviorID);
			if(EventCode!=-1) {
				String AbType = AbBehavior.AbTypejudge(EventCode);
				System.out.println("The abnormal behavior type is:\n"+AbType);
				AbBehavior.AbBehavior_message_get(AbBehaviorID,EventCode);
				System.out.println("Check successfully!");
			}
			else System.out.println("Check failed!");
		}
		else if(option==2) {
			DB_Operation.Connect("abbehavior");
			System.out.println("Please input the abnormal behavior's ID:"); 
			String AbBehaviorID = Input(1);
			if(AbBehavior.Delete_AbBehavior(AbBehaviorID)) 
				System.out.println("Delete successfully!");
			else System.out.println("Delete failed!"); 
		}
		else if(option==3) {
			DB_Operation.Connect("abjudge");
			System.out.println("Please input the abnormal event's ID:");
			String EventID = Input(1);
			int EventType = AbEvent.EventTypeCheck(EventID);
			if(EventType!=-1) {
				String AbType = AbEvent.AbEventTypejudge(EventType);
				System.out.println("The abnormal event type is:\n"+AbType);
				AbEvent.AbEvent_message_get(EventID,EventType);
				System.out.println("Check successfully!");
			}
			else System.out.println("Check failed!");
		}
		else if(option==4) {
			DB_Operation.Connect("abjudge");
			System.out.println("Please input the abnormal event's ID:"); 
			String EventID = Input(1);
			if(AbEvent.Delete_AbEvent(EventID)) 
				System.out.println("Delete successfully!");
			else System.out.println("Delete failed!"); 
		}
		else System.out.println("Input error!"); 
		DB_Operation.Close();
	}
}