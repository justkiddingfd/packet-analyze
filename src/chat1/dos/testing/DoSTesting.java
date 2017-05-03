/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.dos.testing;

import chat1.ducpv.MyIPPacket;
import chat1.others.MyDoSException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DoSTesting {

    public String SynFloodTesting(long ackNumber, boolean isSYN, String ipSource, ArrayList<DoSTestingBean> dosBeanArray) {
        DoSTestingBean dsBean = new DoSTestingBean(ackNumber, isSYN, ipSource);
        if (isSYN) {
            if (dosBeanArray.isEmpty()) {
                dosBeanArray.add(dsBean);
            } else {
                for (DoSTestingBean insiteDoSBean : dosBeanArray) {
                    if ((ipSource.equals(insiteDoSBean.getSourceIP()) &&(ackNumber==insiteDoSBean.getAckNumber()))) {
                        try {
                            insiteDoSBean.setCountBF();
                            return "PASS";
                        } catch (MyDoSException ex) {
                            System.out.println("in fail");
                            return "FAIL";
                        }
                    }
                }
                dosBeanArray.add(dsBean);
                System.out.println("add new bean");
                return "PASS";
            }
        }
        return "PASS";
    }
}
