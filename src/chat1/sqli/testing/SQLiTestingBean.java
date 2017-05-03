/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.sqli.testing;

/**
 *
 * @author Admin
 */
public class SQLiTestingBean {
     //private int countBF = 0;
     private String ipSource;
     private int portSource;
     
     
    SQLiTestingBean(String ipSource, int portSource) {
        this.ipSource = ipSource;
        this.portSource = portSource;
    }

    public String getIpSource() {
        return ipSource;
    }

    public void setIpSource(String ipSource) {
        this.ipSource = ipSource;
    }

    public int getPortSource() {
        return portSource;
    }

    public void setPortSource(int portSource) {
        this.portSource = portSource;
    }
    
}
