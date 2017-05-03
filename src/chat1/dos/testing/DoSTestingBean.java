/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.dos.testing;

import chat1.others.MyDoSException;

/**
 *
 * @author Admin
 */
public class DoSTestingBean {
    
    private String sourceIP;
    private boolean SYN;
    private int countBF = 0;
    private long ackNumber;
        
    public DoSTestingBean(long ack,boolean isSYN, String ipSource) {
        this.SYN = isSYN;
        this.sourceIP = ipSource;
        this.ackNumber = ack;
    }

    public long getAckNumber() {
        return ackNumber;
    }

    public void setAckNumber(long ackNumber) {
        this.ackNumber = ackNumber;
    }

   
    public int getCountBF() {
        return countBF;
    }

    public void setCountBF() throws MyDoSException {
        if(countBF>3){
            throw new MyDoSException("DoS");
        }        
        countBF = countBF+1;
        System.out.println("count:"+countBF);
    }
    public String getSourceIP() {
        return this.sourceIP;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }
  
    public boolean isSYN() {
        System.out.println("is syn? " + this.SYN);
        return this.SYN;
    }

    public void setSYN(boolean SYN) {
        this.SYN = SYN;
    }

   
     
    
}
