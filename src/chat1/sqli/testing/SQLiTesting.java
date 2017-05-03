/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.sqli.testing;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class SQLiTesting {
    public String testSQL(String ipSource, int portSource, String dataTCP, ArrayList<SQLiTestingBean> sqlBeanArray){
        //SQLiTestingBean sqlBean = new SQLiTestingBean(ipSource, portSource);
        //read payload string from file
        String[] payload = {"\'))%20waitfor%20delay%20\'0:0:20\'%20/*","\' or \'1\' = \'1\'","1\' OR \'1\'=\'1","1%20and%20substring(version(),1,1)",
            "1\';select pg_sleep(2); —","\',NULL)%20waitfor%20delay%20\'0:0:20\'%20--","%20waitfor%20delay\'0%3a0%3a20\'--"};
        boolean checkResult = false;
        for (int i = 0; i < payload.length; i++) {
            String string = payload[i];
            System.out.println("string:"+string);
            checkResult = dataTCP.contains(string);
            System.out.println("checkResult:"+checkResult);
            if(checkResult){
                return "FAIL";
            }
        }
        return "PASS";
    }
}
