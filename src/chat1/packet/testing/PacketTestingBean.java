/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.packet.testing;

import chat1.others.MyFTPException;
import chat1.others.MyPacketTestingException;

/**
 *
 * @author Admin
 */
public class PacketTestingBean {
    private byte[] headerData;
    private byte[] dataData;
    private int countBF = 0;

    public int getCountBF() {
        return countBF;
    }
    public void setCountBF() throws MyPacketTestingException {
        if(countBF>2){
            throw new MyPacketTestingException("packettesting");
        }
        
        countBF = countBF+1;
        System.out.println("count:"+countBF);
    }
    
    public PacketTestingBean(byte[] headerData, byte[] dataData) {
        this.headerData = headerData;
        this.dataData = dataData;
    }

    public byte[] getDataData() {
        return dataData;
    }

    public void setDataData(byte[] dataData) {
        this.dataData = dataData;
    }
    
    

    public byte[] getHeaderData() {
        return headerData;
    }

    public void setHeaderData(byte[] headerData) {
        this.headerData = headerData;
    }
    
}
