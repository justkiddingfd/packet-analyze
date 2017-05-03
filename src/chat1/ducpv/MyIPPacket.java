/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.ducpv;

import jpcap.packet.IPPacket;
import jpcap.packet.Packet;

/**
 *
 * @author Admin
 */
public class MyIPPacket {

    private String destinationIPOfTCPPacket;
    private String sourceIPOfTCPPacket;
    private int sourcePortOfTCPPacket;
    private int destinationPortOfTCPPacket;
    private byte[] headerOfTCPPacket;
    private byte[] dataOfTCPPacket;
    private boolean isACK;
    private boolean isRST;
    private boolean isFIN;
    private boolean isSYN;

    public MyIPPacket(Packet packet) {
        IPPacket ipPacket = (IPPacket) packet;
    }

    public String getDestinationIPOfTCPPacket() {
        return destinationIPOfTCPPacket;
    }

    public void setDestinationIPOfTCPPacket(String destinationIPOfTCPPacket) {
        this.destinationIPOfTCPPacket = destinationIPOfTCPPacket;
    }

    public String getSourceIPOfTCPPacket() {
        return sourceIPOfTCPPacket;
    }

    public void setSourceIPOfTCPPacket(String sourceIPOfTCPPacket) {
        this.sourceIPOfTCPPacket = sourceIPOfTCPPacket;
    }

    public int getSourcePortOfTCPPacket() {
        return sourcePortOfTCPPacket;
    }

    public void setSourcePortOfTCPPacket(int sourcePortOfTCPPacket) {
        this.sourcePortOfTCPPacket = sourcePortOfTCPPacket;
    }

    public int getDestinationPortOfTCPPacket() {
        return destinationPortOfTCPPacket;
    }

    public void setDestinationPortOfTCPPacket(int destinationPortOfTCPPacket) {
        this.destinationPortOfTCPPacket = destinationPortOfTCPPacket;
    }

    public byte[] getHeaderOfTCPPacket() {
        return headerOfTCPPacket;
    }

    public void setHeaderOfTCPPacket(byte[] headerOfTCPPacket) {
        this.headerOfTCPPacket = headerOfTCPPacket;
    }

    public byte[] getDataOfTCPPacket() {
        return dataOfTCPPacket;
    }

    public void setDataOfTCPPacket(byte[] dataOfTCPPacket) {
        this.dataOfTCPPacket = dataOfTCPPacket;
    }

    public boolean isIsACK() {
        return isACK;
    }

    public void setIsACK(boolean isACK) {
        this.isACK = isACK;
    }

    public boolean isIsRST() {
        return isRST;
    }

    public void setIsRST(boolean isRST) {
        this.isRST = isRST;
    }

    public boolean isIsFIN() {
        return isFIN;
    }

    public void setIsFIN(boolean isFIN) {
        this.isFIN = isFIN;
    }

    public boolean isIsSYN() {
        return isSYN;
    }

    public void setIsSYN(boolean isSYN) {
        this.isSYN = isSYN;
    }
    
    
}
