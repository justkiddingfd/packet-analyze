/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.ducpv;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

/**
 *
 * @author Admin
 */
public class DemoSend {
    /*
    public static void main(String[] args){
        try {
            sendPacket();
        } catch (IOException ex) {
            Logger.getLogger(DemoSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    * */
    public static void sendPacket() throws java.io.IOException {
        MyNetworkInterface.listNI = JpcapCaptor.getDeviceList();
        System.out.println("Avaiable interfaces: ");
        //MyNetworkInterface.choiceNetworkInterface();
        System.out.println("-------------------------\n");
        //int choice = Integer.parseInt(new Utils().getInput("Choose interface (0,1..): "));
        // testcase:       choice = 1;
        System.out.println("Listening on interface -> " + MyNetworkInterface.listNI[1].description);
        System.out.println("-------------------------\n");
        System.out.println("sending.");
        JpcapSender sender = JpcapSender.openDevice(MyNetworkInterface.listNI[1]);

        TCPPacket p = new TCPPacket(12, 34, 56, 78, false, false, false, false, true, true, true, true, 10, 10);
        p.setIPv4Parameter(0, false, false, false, 0, false, false, false, 0, 1010101, 100, IPPacket.IPPROTO_TCP,
                InetAddress.getByName("172.1.1.100"), InetAddress.getByName("172.1.1.110"));
        p.data = ("dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").getBytes();
        
        EthernetPacket ether = new EthernetPacket();
        ether.frametype = EthernetPacket.ETHERTYPE_IP;
        ether.src_mac = new byte[]{(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5};
        ether.dst_mac = new byte[]{(byte) 0, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10};
        p.datalink = ether;

        for (int i = 0; i < 10; i++) {
            sender.sendPacket(p);
        }
    }
}
