/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.ftp.testing;

import chat1.others.MyFTPException;

/**
 *
 * @author Admin
 */
public class  BruteforceTestingBean {
    private int ipPort;
    private String addressIP;
    private int countBF = 0;

    public int getCountBF() {
        return countBF;
    }

    public void setCountBF() throws MyFTPException {
        if(countBF>5){
            throw new MyFTPException("ftp");
        }
        
        countBF = countBF+1;
        System.out.println("count:"+countBF);
    }
    
    
    public BruteforceTestingBean(int ipPort, String addressIP) {
        this.ipPort = ipPort;
        this.addressIP = addressIP;
        
    }

    public int getIpPort() {
        return ipPort;
    }

    public void setIpPort(int ipPort) {
        this.ipPort = ipPort;
    }

    public String getAddressIP() {
        return addressIP;
    }

    public void setAddressIP(String addressIP) {
        this.addressIP = addressIP;
    }
    
    
}
