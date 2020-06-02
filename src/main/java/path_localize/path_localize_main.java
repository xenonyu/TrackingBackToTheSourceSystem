package path_localize;
import java.sql.SQLException;
import Public.DB_Operation;
//攻击路径定位接口
public class path_localize_main {
	public static boolean Deal_inputdata()throws SQLException, ClassNotFoundException {
		//从数据库inputdata中提取异常行为数据，执行定位后输出到本地异常行为数据库中
		String[]result = new String[2];
		String[][]information = new String[100][2];
		result[0] = "abBehaviorID";
		result[1] = "eventCode";
		information = DB_Operation.SelectMore("*", "inputdata.abbehavior", result, 2);
		for(int i=0; i<100; i++) {
			if(information[0][0]==null) return false;
			else if(information[i][0]==null) break;
			String AbBehaviorID = information[i][0];
			int EventCode = Integer.valueOf(information[i][1]);
			if((EventCode==2)||(EventCode>=9)&&(EventCode<=12)||(EventCode>=15)) {
				if(Check_AbBehaviorID_From_authabtable(AbBehaviorID)==1) {
					switch(EventCode) {
					case 2:{
						if(!Update_judgeby_abtype.AbBehavior_Type02(AbBehaviorID)) return false;
						break;
					}
					case 9:{
						if(!Update_judgeby_abtype.AbBehavior_Type09(AbBehaviorID)) return false;
						break;
					}
					case 10:{
						if(!Update_judgeby_abtype.AbBehavior_Type10(AbBehaviorID)) return false;
						break;
					}
					case 11:{
						if(!Update_judgeby_abtype.AbBehavior_Type11(AbBehaviorID)) return false;
						break;
					}
					case 12:{
						if(!Update_judgeby_abtype.AbBehavior_Type12(AbBehaviorID)) return false;
						break;
					}
					case 15:{
						if(!Update_judgeby_abtype.AbBehavior_Type15(AbBehaviorID)) return false;
						break;
					}
					case 16:{
						if(!Update_judgeby_abtype.AbBehavior_Type16(AbBehaviorID)) return false;
						break;
					}
					case 17:{
						if(!Update_judgeby_abtype.AbBehavior_Type17(AbBehaviorID)) return false;
						break;
					}
					default: return false;
					}
				}
				else {
					switch(EventCode) {
					case 2:{
						if(!Insert_judgeby_abtype.AbBehavior_Type02(AbBehaviorID)) return false;
						break;
					}
					case 9:{
						if(!Insert_judgeby_abtype.AbBehavior_Type09(AbBehaviorID)) return false;
						break;
					}
					case 10:{
						if(!Insert_judgeby_abtype.AbBehavior_Type10(AbBehaviorID)) return false;
						break;
					}
					case 11:{
						if(!Insert_judgeby_abtype.AbBehavior_Type11(AbBehaviorID)) return false;
						break;
					}
					case 12:{
						if(!Insert_judgeby_abtype.AbBehavior_Type12(AbBehaviorID)) return false;
						break;
					}
					case 15:{
						if(!Insert_judgeby_abtype.AbBehavior_Type15(AbBehaviorID)) return false;
						break;
					}
					case 16:{
						if(!Insert_judgeby_abtype.AbBehavior_Type16(AbBehaviorID)) return false;
						break;
					}
					case 17:{
						if(!Insert_judgeby_abtype.AbBehavior_Type17(AbBehaviorID)) return false;
						break;
					}
					default: return false;
					}
				}
			}
			else if((EventCode==0)||(EventCode==1)||(EventCode==4)) {
				if(Check_AbBehaviorID_From_servabtable(AbBehaviorID)==1) {	
					switch(EventCode) {
					case 0:{
						if(!Update_judgeby_abtype.AbBehavior_Type00(AbBehaviorID)) return false;
						break;
					}
					case 1:{
						if(!Update_judgeby_abtype.AbBehavior_Type01(AbBehaviorID)) return false;
						break;
					}
					case 4:{
						if(!Update_judgeby_abtype.AbBehavior_Type04(AbBehaviorID)) return false;
						break;
					}
					default: return false;
					}
				}
				else {
					switch(EventCode) {
					case 0:{
						if(!Insert_judgeby_abtype.AbBehavior_Type00(AbBehaviorID)) return false;
						break;
					}
					case 1:{
						if(!Insert_judgeby_abtype.AbBehavior_Type01(AbBehaviorID)) return false;
						break;
					}
					case 4:{
						if(!Insert_judgeby_abtype.AbBehavior_Type04(AbBehaviorID)) return false;
						break;
					}
					default: return false;
					}
				}
			}
			else {
				if(Check_AbBehaviorID_From_credabtable(AbBehaviorID)==1) {	
					switch(EventCode) {
					case 3:{
						if(!Update_judgeby_abtype.AbBehavior_Type03(AbBehaviorID)) return false;
						break;
					}
					case 5:{
						if(!Update_judgeby_abtype.AbBehavior_Type05(AbBehaviorID)) return false;
						break;
					}
					case 6:{
						if(!Update_judgeby_abtype.AbBehavior_Type06(AbBehaviorID)) return false;
						break;
					}
					case 7:{
						if(!Update_judgeby_abtype.AbBehavior_Type07(AbBehaviorID)) return false;
						break;
					}
					case 8:{
						if(!Update_judgeby_abtype.AbBehavior_Type08(AbBehaviorID)) return false;
						break;
					}
					case 13:{
						if(!Update_judgeby_abtype.AbBehavior_Type13(AbBehaviorID)) return false;
						break;
					}
					case 14:{
						if(!Update_judgeby_abtype.AbBehavior_Type14(AbBehaviorID)) return false;
						break;
					}
					default: return false;
					}
				}
				else {
					switch(EventCode) {
					case 3:{
						if(!Insert_judgeby_abtype.AbBehavior_Type03(AbBehaviorID)) return false;
						break;
					}
					case 5:{
						if(!Insert_judgeby_abtype.AbBehavior_Type05(AbBehaviorID)) return false;
						break;
					}
					case 6:{
						if(!Insert_judgeby_abtype.AbBehavior_Type06(AbBehaviorID)) return false;
						break;
					}
					case 7:{
						if(!Insert_judgeby_abtype.AbBehavior_Type07(AbBehaviorID)) return false;
						break;
					}
					case 8:{
						if(!Insert_judgeby_abtype.AbBehavior_Type08(AbBehaviorID)) return false;
						break;
					}
					case 13:{
						if(!Insert_judgeby_abtype.AbBehavior_Type13(AbBehaviorID)) return false;
						break;
					}
					case 14:{
						if(!Insert_judgeby_abtype.AbBehavior_Type14(AbBehaviorID)) return false;
						break;
					}
					default: return false;
					}
				}
			}
		}
		return true;
	}
	public static int Check_AbBehaviorID_From_authabtable(String AbBehaviorID) throws SQLException {
		//从认证异常表中查找是否已存在异常行为ID（防止主键冲突，相同则更新，不存在则创建）
		String result = DB_Operation.Select("abBehaviorID", "abbehavior.authabtable", "abBehaviorID", "'"+AbBehaviorID+"'", "abBehaviorID");
		if(result!=null) return 1;
		else return 0;
	}
	public static int Check_AbBehaviorID_From_servabtable(String AbBehaviorID) throws SQLException {
		//从服务器异常表中查找是否已存在异常行为ID（防止主键冲突，相同则更新，不存在则创建）
		String result = DB_Operation.Select("abBehaviorID", "abbehavior.servabtable", "abBehaviorID", "'"+AbBehaviorID+"'", "abBehaviorID");
		if(result!=null) return 1;
		else return 0;
	}
	public static int Check_AbBehaviorID_From_credabtable(String AbBehaviorID) throws SQLException {
		//从凭据异常表中查找是否已存在异常行为ID（防止主键冲突，相同则更新，不存在则创建）
		String result = DB_Operation.Select("abBehaviorID", "abbehavior.credabtable", "abBehaviorID", "'"+AbBehaviorID+"'", "abBehaviorID");
		if(result!=null) return 1;
		else return 0;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DB_Operation.Connect("inputdata");
		DB_Operation.Connect("abbehavior");
		if(Deal_inputdata()) System.out.println("Successful!");
		else System.out.println("falied!");
		DB_Operation.Close();
	}
}
