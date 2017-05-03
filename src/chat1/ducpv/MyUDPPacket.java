/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.ducpv;

import jpcap.packet.Packet;
import jpcap.packet.UDPPacket;

/**
 *
 * @author Admin
 */
public class MyUDPPacket {

    private String destinationIPOfUDPPacket;
    private String destinationHostOfUDPPacket;
    private String sourceIPOfUDPPacket;
    private String sourceHostOfUDPPacket;
    private int sourcePortOfUDPPacket;
    private int destinationPortOfUDPPacket;
    private byte[] headerOfUDPPacket;
    private byte[] dataOfUDPPacket;
    
    public MyUDPPacket(Packet packet) {
        UDPPacket udpPacket = (UDPPacket) packet;
        destinationIPOfUDPPacket = udpPacket.dst_ip.getHostAddress();
        destinationHostOfUDPPacket = udpPacket.dst_ip.getHostName();
        sourceIPOfUDPPacket = udpPacket.src_ip.getHostAddress();
        sourceHostOfUDPPacket = udpPacket.src_ip.getHostName();
        destinationPortOfUDPPacket = udpPacket.dst_port;
        sourcePortOfUDPPacket = udpPacket.src_port;
        dataOfUDPPacket = udpPacket.data;
        headerOfUDPPacket = udpPacket.header;
    }

    
    /* 
     * in ra neu goi tin la UDP
     */
    public  void printUDPPacket() throws Exception{
        showPacketInformation();
    }
    private void showPacketInformation() throws Exception {
        System.out.println("\n===================== UDP ===============================");
                /*     */
        String isoData = new String(dataOfUDPPacket, "UTF-8");
        System.out.println(sourceIPOfUDPPacket + "(" + sourceHostOfUDPPacket + "):" + sourcePortOfUDPPacket + " ---> " + destinationIPOfUDPPacket + "(" + destinationHostOfUDPPacket + "):" + destinationPortOfUDPPacket);
        if (!isoData.equals("")) {
            System.out.println("\n" + isoData);
        } else {
            System.out.println("\nNULL");
        }
        //System.out.println("\nthis is destination ip " + destinationIPOfTCPPacket);
        //System.out.println("this is source ip" + sourceIPOfTCPPacket);
        //System.out.println("data: " + new Utils().getHexString(dataOfTCPPacket));
        //System.out.println("data: " + new String(dataOfTCPPacket, "UTF-8"));

    }
}
