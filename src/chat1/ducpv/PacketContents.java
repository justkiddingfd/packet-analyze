/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.ducpv;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import jpcap.*;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
/**
 *
 * @author Admin
 */
public class PacketContents implements PacketReceiver{
    static MyTCPPacket myTCPPacket;
    @Override
    public void receivePacket(Packet packet) {
        if ((packet != null) &&(packet instanceof IPPacket)) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            System.out.printf("\n" + dateFormat.format(date));
            // 
            IPPacket ipPacket = (IPPacket) packet;
            //String sourceIP = ipPacket.src_ip.getHostAddress();
            //String sourceHostName = ipPacket.src_ip.getHostName();
            //String destIP = ipPacket.dst_ip.getHostAddress();
            //String destHostName = ipPacket.dst_ip.getHostName();
            //byte version = ipPacket.version;
            //byte rsvTOS = ipPacket.rsv_tos; // not used
            int identification = ipPacket.ident;
            boolean dFlag = ipPacket.d_flag;
            boolean dontFrag = ipPacket.dont_frag;
            short offSet = ipPacket.offset;
            boolean rFlag = ipPacket.r_flag;
            boolean rsvFrag = ipPacket.rsv_frag;
            boolean moreFrag = ipPacket.more_frag;
            //ipPacket.
            if (ipPacket.protocol == new Contants().TCP) {
                myTCPPacket = new MyTCPPacket(ipPacket);
                System.out.println("\n*===================================================================*");
                System.out.println("dont frag?: " + dontFrag + "|ident:" + identification + "|dFlag:" + dFlag + "|offset:" + offSet + "|fFlag:" + rFlag + "|rsvFlag:" + rsvFrag + "|moreFrag:" + moreFrag);
                //System.out.println(sourceIP+"("+sourceHostName+") ===> ("+destHostName+")"+destIP);
                myTCPPacket.printTCPPacket(null, null);
                /*
                 * 
                 * them vao 18/08
                 * 
                 */
                //ArrayList array = new ArrayList();
                //array.add(myTCPPacket);
            } else if (ipPacket.protocol == new Contants().UDP) {
                //myUDPPacket = new MyUDPPacket(ipPacket);
                //myUDPPacket.printUDPPacket();
            } else if (ipPacket.protocol == new Contants().ICMP) {
                System.out.println("ICMP");
                System.out.println("----------------------------------------------------------------------");
            } else {
                System.out.println("Unsupported Protocol " + ipPacket.protocol);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    }
}
