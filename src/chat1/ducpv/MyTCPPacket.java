/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.ducpv;

import chat1.dos.testing.DoSTesting;
import chat1.dos.testing.DoSTestingBean;
import chat1.ftp.testing.BruteforceTesting;
import chat1.ftp.testing.BruteforceTestingBean;
import chat1.packet.testing.PacketTesting;
import chat1.packet.testing.PacketTestingBean;
import chat1.sqli.testing.SQLiTesting;
import chat1.sqli.testing.SQLiTestingBean;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;

/**
 *
 * @author Admin
 */
public class MyTCPPacket {

    private String destinationIPOfTCPPacket;
    private String sourceIPOfTCPPacket;
    private String destinationHostOfTCPPacket;
    private String sourceHostOfTCPPacket;
    private int sourcePortOfTCPPacket;
    private int destinationPortOfTCPPacket;
    private byte[] headerOfTCPPacket;
    private byte[] dataOfTCPPacket;
    private boolean isACK;
    private boolean isRST;
    private boolean isFIN;
    private boolean isSYN;
    private short protocol;
    private long sequenceNumber;
    private long ackNumber;
    private String dateTimeOfPacket;
    private short offsetNumber;
    private int identNumber;
    private String isoDataOfPacket;
    private BruteforceTesting bruteforceTesting;
    private BruteforceTestingBean bfBean;
    public ArrayList<BruteforceTestingBean> arrayList = new ArrayList<BruteforceTestingBean>();

    public MyTCPPacket(IPPacket packet) {
        try {
            TCPPacket tcpPacket = (TCPPacket) packet;

            offsetNumber = tcpPacket.offset;
            identNumber = tcpPacket.ident;
            destinationIPOfTCPPacket = tcpPacket.dst_ip.getHostAddress();
            sourceIPOfTCPPacket = tcpPacket.src_ip.getHostAddress();
            destinationHostOfTCPPacket = tcpPacket.dst_ip.getHostName();
            sourceHostOfTCPPacket = tcpPacket.src_ip.getHostName();
            sourcePortOfTCPPacket = tcpPacket.src_port;
            destinationPortOfTCPPacket = tcpPacket.dst_port;
            sequenceNumber = tcpPacket.sequence;
            ackNumber = tcpPacket.ack_num;
            headerOfTCPPacket = tcpPacket.header;
            dataOfTCPPacket = tcpPacket.data;
            isACK = tcpPacket.ack;
            isRST = tcpPacket.rst;
            isFIN = tcpPacket.fin;
            isSYN = tcpPacket.syn;
            protocol = packet.protocol;

            //byte version = tcpPacket.version;
            //byte rsvTOS = tcpPacket.rsv_tos; // not used
            //int identification = tcpPacket.ident;
            //boolean dFlag = tcpPacket.d_flag;
            //boolean dontFrag = tcpPacket.dont_frag;
            //short offSet = tcpPacket.offset;
            //boolean rFlag = tcpPacket.r_flag;
            //boolean rsvFrag = tcpPacket.rsv_frag;
            //boolean moreFrag = tcpPacket.more_frag;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            dateTimeOfPacket = dateFormat.format(date);
            //System.out.printf("\n" + dateTimeOfPacket);
            //System.out.println("|||||dont frag?: "+dontFrag+"|ident:"+identification+"|dFlag:"+dFlag+"|offset:"+offSet+"|fFlag:"+rFlag+"|rsvFlag:"+rsvFrag+"|moreFrag:"+moreFrag);
            isoDataOfPacket = new String(dataOfTCPPacket, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MyTCPPacket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /*
     * in ra neu goi tin la TCP
     */
    public void printTCPPacket(String ipDestAddress, String ipSourceAddress) throws Exception {
        if ((ipDestAddress == null) && (ipSourceAddress == null)) {
            //System.out.println(" /n this is TCP packet");
            showPacketInformation();
        } else if (destinationIPOfTCPPacket.equalsIgnoreCase(ipDestAddress) || sourceIPOfTCPPacket.equalsIgnoreCase(ipDestAddress)) {
            // System.out.println(" /n this is TCP packet");
            showPacketInformation();
        }

    }

    public String[] returnTCPPacket(String ipDestAddress, String ipSourceAddress) throws Exception {
        String[] inforPacket = null;
        if ((ipDestAddress == null) && (ipSourceAddress == null)) {
            //System.out.println(" /n this is TCP packet");
            inforPacket = getPacketInformation();
        } else if (destinationIPOfTCPPacket.equalsIgnoreCase(ipDestAddress) || sourceIPOfTCPPacket.equalsIgnoreCase(ipDestAddress)) {
            // System.out.println(" /n this is TCP packet");
            inforPacket = getPacketInformation();
        }
        return inforPacket;
    }

    private void showPacketInformation() throws Exception {
        //System.out.println("this is destination port of tcp :" + destinationPortOfTCPPacket);
        //System.out.println("header: " + new Utils().getHexString(headerOfTCPPacket));
        //System.out.println("\n====================================================");
        //System.out.print("*||| sequence number:" + sequenceNumber + "    |ack number:" + ackNumber);
        if (isACK) {
            System.out.println("\n" + "ACK");
        } else {
            System.out.println("this is not an ACK packet");
        }

        if (isRST) {
            System.out.println("reset connection ");
        }

        if (isFIN) {
            System.out.println("sender does not have more data to transfer");
        }
        if (isSYN) {
            System.out.println("\n request for connection");
        }
        /*     */

        System.out.println(sourceIPOfTCPPacket + "(" + sourceHostOfTCPPacket + "):" + sourcePortOfTCPPacket + " ---> " + destinationIPOfTCPPacket + "(" + destinationHostOfTCPPacket + "):" + destinationPortOfTCPPacket);
        if (!isoDataOfPacket.equals("")) {
            System.out.println("\n" + isoDataOfPacket);
        } else {
            System.out.println("\nNULL");
        }
        //System.out.println("\nthis is destination ip " + destinationIPOfTCPPacket);
        //System.out.println("this is source ip" + sourceIPOfTCPPacket);
        //System.out.println("data: " + new Utils().getHexString(dataOfTCPPacket));
        //System.out.println("data: " + new String(dataOfTCPPacket, "UTF-8"));

    }

    private String[] getPacketInformation() {
        String sourcePort = Integer.toString(sourcePortOfTCPPacket);
        String destPort = Integer.toString(destinationPortOfTCPPacket);
        try {
            showPacketInformation();
        } catch (Exception ex) {
            Logger.getLogger(MyTCPPacket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String[]{dateTimeOfPacket, sourceIPOfTCPPacket, sourcePort, destinationIPOfTCPPacket, destPort};
    }

    public String testingSQL(ArrayList<SQLiTestingBean> sqlBeanArray) {
        if ((destinationPortOfTCPPacket == 80) || (destinationPortOfTCPPacket == 8080) || (destinationPortOfTCPPacket == 8084)) {
            return new SQLiTesting().testSQL(sourceIPOfTCPPacket, sourcePortOfTCPPacket, isoDataOfPacket, sqlBeanArray);
        } else {
            return "PASS";
        }
    }

    public String testingBruteforce(ArrayList<BruteforceTestingBean> bfBeanArray) {
        if (destinationPortOfTCPPacket != 21) {
            return "PASS";
        } else {
            return new BruteforceTesting().testBruteForce(sourceIPOfTCPPacket, sourcePortOfTCPPacket, isoDataOfPacket, bfBeanArray);
        }
    }

    public String testingDoS(ArrayList<DoSTestingBean> dosBeanArray) {
        return new DoSTesting().SynFloodTesting(this.ackNumber, this.isSYN, this.getSourceIPOfTCPPacket(), dosBeanArray);
    }

    public String testLapGoi(ArrayList<PacketTestingBean> ptBeanArray) {

        return new PacketTesting().testPacket(headerOfTCPPacket, dataOfTCPPacket, ptBeanArray);
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

    public String getDestinationHostOfTCPPacket() {
        return destinationHostOfTCPPacket;
    }

    public void setDestinationHostOfTCPPacket(String destinationHostOfTCPPacket) {
        this.destinationHostOfTCPPacket = destinationHostOfTCPPacket;
    }

    public String getSourceHostOfTCPPacket() {
        return sourceHostOfTCPPacket;
    }

    public void setSourceHostOfTCPPacket(String sourceHostOfTCPPacket) {
        this.sourceHostOfTCPPacket = sourceHostOfTCPPacket;
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

    public short getProtocol() {
        return protocol;
    }

    public void setProtocol(short protocol) {
        this.protocol = protocol;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public long getAckNumber() {
        return ackNumber;
    }

    public void setAckNumber(long ackNumber) {
        this.ackNumber = ackNumber;
    }

    public String getDateTimeOfPacket() {
        return dateTimeOfPacket;
    }

    public void setDateTimeOfPacket(String dateTimeOfPacket) {
        this.dateTimeOfPacket = dateTimeOfPacket;
    }

    public short getOffsetNumber() {
        return offsetNumber;
    }

    public void setOffsetNumber(short offsetNumber) {
        this.offsetNumber = offsetNumber;
    }

    public int getIdentNumber() {
        return identNumber;
    }

    public void setIdentNumber(int identNumber) {
        this.identNumber = identNumber;
    }

    public String getIsoDataOfPacket() {
        return isoDataOfPacket;
    }

    public void setIsoDataOfPacket(String isoDataOfPacket) {
        this.isoDataOfPacket = isoDataOfPacket;
    }
}
