一，本程序使用Java编写，在Eclipse平台上可运行。


二，数据库支持：

1. MySQL；用户名：root，密码：password。需要有以下数据库和表，详情见文档：
2. Abbehavior（异常行为）数据库：
> 1. 表：authabtable，存放认证异常行为相关信息；（空表）
> 2. 表：credabtable，存放凭据异常行为相关信息；）
> 3. 表：servabtable，存放服务器异常行为相关信息。

3. abjudge（安全事件判定）数据库：
只有表judgetable，存放安全事件相关信息。

4. adcodedatabase（行政区划编码）数据库：
	为使用“行政区划编码”进行定位的功能保留，只有表adcodetable，存放行政区划编码及其对应的地区名称。表中包括adcode，areaname两列，adcode为主键，类型均为varchar。
	举例说明：adcode = ‘110000’, areaname = ‘北京市’。

5. inputdata（输入数据）数据库：
	存放程序的输入数据，包括异常行为的数据与安全事件的数据。
（1）表：abbehavior，存放输入的异常行为的相关数据；
（2）表：abjudge，存放输入的安全事件的相关数据。
	数据格式与Abbehavior、abjudge数据库中的表相同，只不过把其中存放的area信息更换成了IP信息。

6. ipdatabase（IP）数据库：
	存放IP及其对应的区域信息，根据IP查询区域为二级查询，根据这个功能需求分为两种表：
（1）tableXXXX：XXXX为0000~4999，共5000个表，每个表约4700条数据，共约23000000条数据。数据格式如下：
1）id：int，长度11，不是NULL，主键，表示第几条数据；
2）minip：int，长度11，表示该区域对应的最小整数型IP；
3）maxip：int，长度11，表示该区域对应的最大整数型IP；
4）continent：varchar，长度16，表示对应IP所在的大洲；
5）areacode：varchar，长度4，表示对应IP所在国家的代号；
6）country：varchar，长度50，表示对应IP所在国家的名称；
7）multiarea：text，表示对应IP所在国家的详细信息，包括省，市，区，县，经纬度。
8）user：varchar，表示对应IP使用的用户名。
举例说明：id = 950，minip = 17564672，maxip = 17565183，continent = ’亚洲’，areacode = ’CN’，country = ’中国’，multiarea = ’ "w":"39.95143","j":"116.509342","p":"北京市","c":"北京市","d":"朝阳区" ’，user = ‘Beijing Founder Broadband Network Technology Co.,Ltd’。
（2）tableindex：存放table0000~table4999的索引信息，用于二级查询。列名包括tableid，minip，maxip，类型均为int，长度均为11，tableid为主键。
1）tableid：表的序号，0-4999；
2）minip：该表所对应IP范围的最小值；
2）maxip：该表所对应IP范围的最大值；
	举例说明：tableid = 1，minip = 17659608，maxip = 17723164。

7. register（注册信息）数据库：
	存放用户的注册信息与对系统的操作信息（操作存日志）。
（1）registmessage表：存放用户的注册信息，两列user与password，格式均为varchar，不能为空，user为主键。
（2）journal表：日志，存放用户的操作信息，三列user、time与event，格式均为varchar，不能为空，time为主键。


三，代码部分：
	代码部分分为7个包，abnormal_process、areacheck、externalfunction、individual_judge、path_localize、public、useit。并引用用于连接数据库的lib：mysql-connector-java-5.1.45-bin.jar。

1. public：只有一个文件DB_Operation.java，实现对数据库的公共操作，方法如下（参数省略）：
1）connect：数据库连接；
2）close：数据库关闭；
3）select：执行数据库中的select语句；
4）insert：执行数据库中的insert语句；
5）update：执行数据库中的update语句；
6）delete：执行数据库中的delete语句。

2. areacheck：实现对区域的查询，包含两个文件adcodecheck.java与IPcheck.java
（1）adcodecheck.java：实现基于行政区划编码对区域的查询，查询基于数据库adcodedatabase，可独立运行。
（2）IPcheck.java：实现基于IP地址对区域的查询，查询基于数据库IPdatabase，方法包括（参数省略）：
1）input：用户输入；
2）iptranslate：实现IP地址从点分到整数的转换；
3）tablecheck：查询目标IP地址的所在表（因为是二级查询，先查tableindex）；
4）informationcheck：查询目标表中的IP地址信息（查tableXXXX）；
5）chartran：查询结果可能会出现转义字符，对其进行处理；

3. path_localize：实现攻击路径定位接口，包含3个文件：path_localize_main.java，insert_judgeby_abtype.java，update_judgeby_abtype.java。
（1）path_localize_main.java：接口主要执行部分，包括以下方法：
1）Deal_inputdata：从数据库inputdata中提取异常行为数据，执行定位后输出到本地异常行为数据库中；
2）Check_AbBehaviorID_From_authabtable：从认证异常表中查找是否已存在异常行为ID（防止主键冲突，相同则更新，不存在则创建）；
3）Check_AbBehaviorID_From_servabtable：对服务器异常表，其余同上；
4）Check_AbBehaviorID_From_credabtable：对凭据异常表，其余同上；
（2）insert_judgeby_abtype.java：向本地异常行为数据库中插入数据，其中不同异常行为（18种）插入的数据不同。
（3）update_judgeby_abtype.java ：向本地异常行为数据库中更新数据。

4. individual_judge：实现异常个体/区域判定接口，包含2个文件：individual_judge_main.java与modify_judgeby_eventtype.java。
（1）individual_judge_main.java：接口主要执行部分，包括以下方法：
1）Deal_inputdata：从数据库inputdata中提取异常事件数据，执行定位后输出到本地异常行为数据库中；
2）Check_EventID_From_judgetable：从异常事件表中查找是否已存在异常事件ID（防止主键冲突，相同则更新，不存在则创建）；
（2）modify_judgeby_eventtype.java：向本地异常事件数据库中插入或更新数据

5. abnormal_process：实现应急联动处置接口，仅包含一个文件abnormal_process_main.java，方法如下：
1）AuthAbBehavior：列出所有认证异常行为的信息；
2）CredAbBehavior：列出所有凭据异常行为的信息；
3）ServAbBehavior：列出所有服务器异常行为的信息；
4）AbEvent：列出所有异常事件的信息。

6. externalfunction：实现一些对本地异常行为或异常事件数据库操作的外部功能，包含4个文件：AbBehavior.java、AbEvent.java、ExternalFunction_main.java、PathCheck_judgeby_abtype.java，可单独执行。
（1）ExternalFunction_main.java：主程序，可以对本地数据库（异常行为数据库与异常事件数据库）进行查询和删除的操作，方法如下：
1）input：输入方法；
2）selectfunctions：选择功能，包括查询和删除。
（2）AbBehavior.java：异常行为的查询与删除，方法如下：
1）EventCodeCheck：查询异常行为编码（因为这里需要通过异常行为类型来查询不同的输出信息）；
2）AbTypejudge：返回不同的异常行为编码对应的中文名称；
3）AbBehavior_message_get：根据不同的异常行为ID与编码，输出不同格式的查询信息；
4）Delete_AbBehavior：删除某ID对应的异常行为。
（3）PathCheck_judgeby_abtype.java ：查询不同异常行为对应的区域信息
（4）AbEvent.java：异常事件的查询与删除，方法如下：
1）EventTypeCheck：查询异常事件的类型；
2）AbEventTypejudge：返回不同的异常事件类型对应的中文名称；
3）AbEvent_message_get：根据不同的异常事件ID与类型，输出不同格式的查询信息；
4）Delete_AbEvent：删除某ID对应的异常事件；

7. useit：真正使用该系统的接口，分为三个文件：journal.java、login_regist.java、main_pro.java。
（1）main_oro.java：主程序执行入口，方法如下：
1）input：用户输入；
2）select_login_regist：打开后选择登录或者注册；
3）select_function：选择功能，包括调用三个文档所写的接口与外部功能。
（2）journal.java：对登录以及注册的用户，记录其操作情况（写日志），分为以下两个方法：
1）gettime：获取当前系统时间；
2）write：将当前操作用户，事件和具体操作写入日志数据库。
（3）login_regist：提供登录和注册功能，方法包括：
1）input：输入方法；
2）regist：注册方法；
3）login：登录方法。

