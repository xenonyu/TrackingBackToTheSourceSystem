package abnormal_process;
import java.sql.SQLException;
import Public.DB_Operation;
//应急联动处置接口
public class AbnormalProcessMain {
	public static void AuthAbBehavior() throws SQLException {
		//列出所有认证异常行为的信息
		String[]ColumnName = new String[13];
		String[][]information = new String[100][13];
		ColumnName[0] = "abBehaviorID";
		ColumnName[1] = "eventCode";
		ColumnName[2] = "authSysID";
		ColumnName[3] = "authSysAreaName";
		ColumnName[4] = "appID";
		ColumnName[5] = "appAreaName";
		ColumnName[6] = "appUserID";
		ColumnName[7] = "credCheckUserID";
		ColumnName[8] = "sysUserID";
		ColumnName[9] = "authCertiUserID";
		ColumnName[10] = "authCertiSysID";
		ColumnName[11] = "authCertiSysAreaName";
		ColumnName[12] = "certiOwnerID";
		information = DB_Operation.SelectMore("*", "abbehavior.authabtable", ColumnName, 13);
		if(information[0][0]!=null) 
			System.out.println("The authentication abnormal behavior messages are as follows:");
		for(int i=0; i<100; i++) {
			if(information[i][0]==null) break;
			for(int j=0; j<13; j++)
				System.out.print(ColumnName[j]+":"+information[i][j]+"  ");
			System.out.println();
		}
	}
	public static void CredAbBehavior() throws SQLException {
		//列出所有凭据异常行为的信息
		String[]ColumnName = new String[11];
		String[][]information = new String[100][11];
		ColumnName[0] = "abBehaviorID";
		ColumnName[1] = "eventCode";
		ColumnName[2] = "credIssueUserID";
		ColumnName[3] = "credReceptUser";
		ColumnName[4] = "credIssueEntID";
		ColumnName[5] = "entAppID";
		ColumnName[6] = "entAppAreaName";
		ColumnName[7] = "credReimUserID";
		ColumnName[8] = "credUseUser";
		ColumnName[9] = "credReimEntID";
		ColumnName[10] = "credURL";
		information = DB_Operation.SelectMore("*", "abbehavior.credabtable", ColumnName, 11);
		if(information[0][0]!=null) 
			System.out.println("The credentials abnormal behavior messages are as follows:");
		for(int i=0; i<100; i++) {
			if(information[i][0]==null) break;
			for(int j=0; j<11; j++)
				System.out.print(ColumnName[j]+":"+information[i][j]+"  ");
			System.out.println();
		}
	}
	public static void ServAbBehavior() throws SQLException {
		//列出所有服务器异常行为的信息
		String[]ColumnName = new String[7];
		String[][]information = new String[100][7];
		ColumnName[0] = "abBehaviorID";
		ColumnName[1] = "eventCode";
		ColumnName[2] = "sysAreaName";
		ColumnName[3] = "fakeSysID";
		ColumnName[4] = "sysUserID";
		ColumnName[5] = "personalAppID";
		ColumnName[6] = "personalAppArea";
		information = DB_Operation.SelectMore("*", "abbehavior.servabtable", ColumnName, 7);
		if(information[0][0]!=null) 
			System.out.println("The server abnormal behavior messages are as follows:");
		for(int i=0; i<100; i++) {
			if(information[i][0]==null) break;
			for(int j=0; j<7; j++)
				System.out.print(ColumnName[j]+":"+information[i][j]+"  ");
			System.out.println();
		}
	}
	public static void AbEvent() throws SQLException {
		//列出所有异常事件的信息
		String[]ColumnName = new String[12];
		String[][]information = new String[100][12];
		String[][]event0inform = new String[100][12];
		String[][]event1inform = new String[100][12];
		ColumnName[0] = "eventID";
		ColumnName[1] = "eventType";
		ColumnName[2] = "eventCode";
		ColumnName[3] = "sysUserID";
		ColumnName[4] = "appID";
		ColumnName[5] = "appAreaName";
		ColumnName[6] = "entID";
		ColumnName[7] = "entAreaName";
		ColumnName[8] = "credIssueEntID";
		ColumnName[9] = "credChaStaEntID";
		ColumnName[10] = "credIssueEntAreaName";
		ColumnName[11] = "credChaStaEntAreaName";
		information = DB_Operation.SelectMore("*", "abjudge.judgetable", ColumnName, 12);
		int event0num=0,event1num=0;
		for(int i=0; i<100; i++) {
			if(information[i][0]==null) break;
			if(information[i][1].equals("0")) {
				for(int j=0; j<8; j++) 
					event0inform[event0num][j] = information[i][j];
				event0num++;
			}
			else {
				for(int j=0; j<4; j++) 
					event1inform[event1num][j] = information[i][j];
				for(int j=8; j<12; j++) 
					event1inform[event1num][j-4] = information[i][j];
				event1num++;
			}
		}
		if(event0num!=0) 
			//同一用户或同一企业的多重异常信息
			System.out.println("The multiple abnormality of the same user or the same enterprise messages are as follows:");
			for(int i=0; i<event0num; i++) {
				for(int j=0; j<8; j++)
					System.out.print(ColumnName[j]+":"+event0inform[i][j]+"  ");
				System.out.println();
			}
		if(event1num!=0)
			//不同企业或不同用户的异常行为信息关联
			System.out.println("The Abnormal behavior association of different enterprises or different users messages are as follows:");
			for(int i=0; i<event1num; i++) {
				for(int j=0; j<4; j++)
					System.out.print(ColumnName[j]+":"+event1inform[i][j]+"  ");
				for(int j=4; j<8; j++)
					System.out.print(ColumnName[j+4]+":"+event1inform[i][j]+"  ");
			System.out.println();
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DB_Operation.Connect("abbehavior");
		AuthAbBehavior();
		CredAbBehavior();
		ServAbBehavior();
		DB_Operation.Connect("abjudge");
		AbEvent();
	}
}