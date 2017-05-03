/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.ducpv;

import java.util.ArrayList;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.*;

/**
 *
 * @author ducpv
 */
public class TestCase11_bk {
    /* start ten bien */

    static JpcapCaptor CAPTOR;
    static NetworkInterface[] listNI;
    static String str, info;
    static int x, choice;
    static MyTCPPacket myTCPPacket;
    static MyUDPPacket myUDPPacket;
    /* start ten bien */

    public static void main(String args[]) {
        try {
            MyNetworkInterface.listNI = JpcapCaptor.getDeviceList();
            System.out.println("Avaiable interfaces: ");
            MyNetworkInterface.choiceNetworkInterface();
            System.out.println("-------------------------\n");
            choice = Integer.parseInt(new Utils().getInput("Choose interface (0,1..): "));
//          choice = 1;
            System.out.println("Listening on interface -> " + MyNetworkInterface.listNI[choice].description);
            System.out.println("-------------------------\n");
            try {
                /* setup device listener */
                CAPTOR = JpcapCaptor.openDevice(MyNetworkInterface.listNI[choice], 65535, false, 20);
                /* listen for TCP/IP only */
//                CAPTOR.setFilter("IP", true);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            /* start listenning */
            while (true) {
                Packet packet = CAPTOR.getPacket();
                if ((packet != null) &&(packet instanceof IPPacket)) {
                    getPacketText(packet);
//                    System.out.println("================================");
//                    System.out.println("this is packet umber: " + i1++);
//                    System.out.println("length: " + packet.len);
//                    System.out.println("detail: " + packet.header.length + " and " + packet.data.length);
                    //System.out.println("full data: " + new Utils().getHexString(packet.header) + new Utils().getHexString(packet.data));                 
                    // System.out.println(getHexString(packet.header));
                    //System.out.println(packet.header.length);

                }
            }
        } catch (Exception e1) {
            System.out.println(e1.toString());
        }
    }

    private static void getPacketText(Packet packet) {
        /* start comment 1 */
//        int i = 0, j = 0;
//        byte[] bytes = new byte[packet.header.length + packet.data.length];
//        System.arraycopy(packet.header, 0, bytes, 0, packet.header.length);
//        System.arraycopy(packet.data, 0, bytes, packet.header.length, packet.data.length);
//        StringBuffer buffer = new StringBuffer();
//        for (i = 0; i < bytes.length;) {
//            for (j = 0; j < 8 && i < bytes.length; j++, i++) {
//                String d = Integer.toHexString((int) (bytes[i] & 0xff));
//                buffer.append((d.length() == 1 ? "0" + d : d) + " ");
//                if (bytes[i] < 32 || bytes[i] > 126) {
//                    bytes[i] = 46;
//                }
//            }
//        }
//        return new String(bytes, i - j, j);        
        /* end comment 1 */

        //String protocol_array[] = {"HOPOPT", "ICMP", "IGMP", "GGP", "IPV4", "ST", "TCP", "CBT", "EGP", "IGP", "BBN", "NV2", "PUP", "ARGUS", "EMCON", "XNET", "CHAOS", "UDP", "mux"};
        try {
            IPPacket ipPacket = (IPPacket) packet;
            String sourceIP = ipPacket.src_ip.getHostAddress();
            String sourceHostName = ipPacket.src_ip.getHostName();
            String destIP = ipPacket.dst_ip.getHostAddress();
            String destHostName = ipPacket.dst_ip.getHostName();
            byte version = ipPacket.version;
            byte rsvTOS = ipPacket.rsv_tos; // not used
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
                System.out.println("dont frag?: "+dontFrag+"|ident:"+identification+"|dFlag:"+dFlag+"|offset:"+offSet+"|fFlag:"+rFlag+"|rsvFlag:"+rsvFrag+"|moreFrag:"+moreFrag);
                //System.out.println(sourceIP+"("+sourceHostName+") ===> ("+destHostName+")"+destIP);
                myTCPPacket.printTCPPacket(null, null);
                /*
                 * 
                 * them vao 18/08
                 * 
                 */
                ArrayList array = new ArrayList();
                array.add(myTCPPacket);
            } else if (ipPacket.protocol == new Contants().UDP) {
                //myUDPPacket = new MyUDPPacket(ipPacket);
                //myUDPPacket.printUDPPacket();
            } else if (ipPacket.protocol == new Contants().ICMP) {
                System.out.println("ICMP");
                /*
                 String headerOfICMP = new Utils().getHexString(ipPacket.header);
                 String dataOfICMP = new Utils().getHexString(ipPacket.data);
                 System.out.println("Source IP: "  + ipPacket.src_ip.toString().substring(1));
                 System.out.println("Destination IP: "  + ipPacket.dst_ip.toString().substring(1));
                 System.out.println("Header cua goi tin:" + headerOfICMP);
                 System.out.println("Data cua goi tin:" + dataOfICMP);
                 if(dataOfICMP.contains(headerOfICMP)){
                 System.out.println("co0000000000000000");
                 }
                 System.out.println("Data cua goi tin:" + new String(ipPacket.data));
                 * */
                System.out.println("----------------------------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //return null;
    }
} /* end class */
