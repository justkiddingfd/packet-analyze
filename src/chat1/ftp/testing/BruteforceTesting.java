/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.ftp.testing;

import chat1.others.MyFTPException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BruteforceTesting {

    public String testBruteForce(String ipSource, int portSource, String dataTCP, ArrayList<BruteforceTestingBean> bfBeanArray) {
        BruteforceTestingBean bfBean = new BruteforceTestingBean(portSource, ipSource);
        //System.out.println("intestingbruteforce: "+dataTCP);
        String arg[] = dataTCP.split(" ");
        String strNumberResponse = arg[0];
        //String strNumberResponse1 = arg[1];
        //System.out.println("numer1: " + strNumberResponse +" |number2:" +strNumberResponse1);
        if (strNumberResponse.equals("PASS")) {
            // kiem tra ip xem da co trong ArrayList chua? Neu roi tih nang bien "count" len, khi biens count >5 thi tra ve fail
            System.out.println("in equal PASSword");
            if (bfBeanArray.isEmpty()) {
                bfBeanArray.add(bfBean);
                System.out.println("in empty");
            } else {
                
                for (BruteforceTestingBean insiteBFBean : bfBeanArray) {
                    if (ipSource.equals(insiteBFBean.getAddressIP())) {
                        try {
                            insiteBFBean.setCountBF();
                            System.out.println("in raise count");
                            return "PASS";
                        } catch (MyFTPException ex) {
                            //System.out.println(ex.toString());
                            //ex.printStackTrace();
                            System.out.println("in FAIL");
                            return "FAIL";
                        }
                    }
                }
                bfBeanArray.add(bfBean);
                System.out.println("add new bfBean");
                return "PASS";
            }
            // neu bien "count" <5 thi tra ve pass
            // neu nhu ip chua co trong ArrayList thi add vao ArrayList
            //return "FAIL";
        }
        return "PASS";

    }
}
