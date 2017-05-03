/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.packet.testing;

import chat1.others.MyPacketTestingException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Admin
 */
public class PacketTesting {
    public String testPacket(byte[] headerofTCP,byte[] dataofTCP, ArrayList<PacketTestingBean> ptBeanArray) {
        PacketTestingBean ptBean = new PacketTestingBean(headerofTCP,dataofTCP);
        if (ptBeanArray.isEmpty()) {
            ptBeanArray.add(ptBean);
            System.out.println("in empty");
        } else {
            for (PacketTestingBean insitePTBean : ptBeanArray) {
                if ((Arrays.equals(headerofTCP, insitePTBean.getHeaderData()) && (Arrays.equals(dataofTCP, insitePTBean.getDataData())))) {
                    try {
                            insitePTBean.setCountBF();
                            System.out.println("in raise count");
                            return "PASS";
                        } catch (MyPacketTestingException ex) {
                            //ex.printStackTrace();
                            return "FAIL";
                        }
                }
            }
            ptBeanArray.add(ptBean);
            System.out.println("add new ptBean");
            return "PASS";
        }
        return "PASS";
    }
}
