/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.frame;

import chat1.dos.testing.DoSTestingBean;
import chat1.ducpv.Contants;
import chat1.ducpv.MyNetworkInterface;
import chat1.ducpv.MyTCPPacket;
import chat1.ducpv.MyUDPPacket;
import chat1.ducpv.PacketContents;
import chat1.ducpv.Utils;
import chat1.ftp.testing.BruteforceTestingBean;
import chat1.packet.testing.PacketTestingBean;
import chat1.sqli.testing.SQLiTestingBean;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

/**
 *
 * @author Admin
 */
public class Frame1 extends javax.swing.JFrame {
    /* start ten bien */

    boolean captureState;
    static JpcapCaptor CAPTOR;
    static NetworkInterface[] listNI;
    static String str, info;
    static MyTCPPacket myTCPPacket;
    static MyUDPPacket myUDPPacket;
    Packet packet;
    String thumucResult = "/results";
    File folder = new File(thumucResult);
    /* start ten bien */

    /**
     * Creates new form Frame1
     */
    public Frame1() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Frame1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Frame1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Frame1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Frame1.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();

        functionOnLoad();
        captureState = false;
        if (CAPTOR != null) {
            CAPTOR.close();
        }
    }
    DefaultTableModel dtm = null;
    public ArrayList<BruteforceTestingBean> bfBeanArray = new ArrayList<BruteforceTestingBean>();
    public ArrayList<PacketTestingBean> ptBeanArray = new ArrayList<PacketTestingBean>();
    public ArrayList<DoSTestingBean> dosBeanArray = new ArrayList<DoSTestingBean>();
    public ArrayList<SQLiTestingBean> sqlBeanArray = new ArrayList<SQLiTestingBean>();

    private void functionOnLoad() {
        //kiem tra xem co thu muc result chua neu co thi bo qua, neu chua thi tao moi
        if (!folder.exists()) {
            //tao folder
            boolean successfull = folder.mkdir();
            if (successfull) {
                System.out.println("OK");
            } else {
                System.out.println("ERROR");
            }
        }

        dtm = (DefaultTableModel) tblPackets.getModel();
        //dtm.ad
        fillNICOption();
        //action cua row

        tblPackets.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                int selectedRow = target.getSelectedRow();
                //int[] selectedColumns = target.getSelectedColumns();
                String selectedAck = String.valueOf(target.getValueAt(selectedRow, 0));
                String selectedIdenti = String.valueOf(target.getValueAt(selectedRow, 1));
                String selectedTime = String.valueOf(target.getValueAt(selectedRow, 2));
                String selectedFrom = String.valueOf(target.getValueAt(selectedRow, 3)) + ":" + String.valueOf(target.getValueAt(selectedRow, 4));

                if (e.getClickCount() == 1) {
                    jTextArea1.setText("");
                    String content = new Utils().readFromFile("C:/" + thumucResult + "/" + selectedAck + ".txt");
                    StringBuilder builder = new StringBuilder();
                    builder.append("Noi dung");
                    builder.append("\n");
                    builder.append(content);
                    jTextArea1.setText(builder.toString());
                }


                if (e.getClickCount() == 2) {
                    // doc file
                    //String content = new Utils().readFromFile("C:/" + thumucResult + "/" + selectedAck + ".txt");
                    //
                    String content = "Time: " + selectedTime + "\n" + selectedFrom;
                    JOptionPane.showMessageDialog(null, content, "Title", JOptionPane.INFORMATION_MESSAGE);
                    StringBuilder builder = new StringBuilder();
                    builder.append(content);
                    jTextArea1.setText(builder.toString());
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cbNICOptions = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPackets = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnExit = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIPAddress = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHostname = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfString = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chương trình thực nghiệm");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Config"));

        cbNICOptions.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chon NIC" }));

        jLabel1.setText("Chon NIC");

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbNICOptions, 0, 866, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnStart)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNICOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStart))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ket qua"));

        tblPackets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ack Number", "Ident", "Time", "Source IP", "Source Port", "Destination IP", "Destination Port", "SQL Testing", "Bruteforce Testing", "Packet Testing", "DoS Testing"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPackets);
        tblPackets.getColumnModel().getColumn(1).setResizable(false);
        tblPackets.getColumnModel().getColumn(2).setResizable(false);
        tblPackets.getColumnModel().getColumn(3).setResizable(false);
        tblPackets.getColumnModel().getColumn(4).setResizable(false);
        tblPackets.getColumnModel().getColumn(5).setResizable(false);
        tblPackets.getColumnModel().getColumn(6).setResizable(false);
        tblPackets.getColumnModel().getColumn(7).setResizable(false);
        tblPackets.getColumnModel().getColumn(8).setResizable(false);
        tblPackets.getColumnModel().getColumn(0).setMinWidth(0);
        tblPackets.getColumnModel().getColumn(0).setMaxWidth(0);
        tblPackets.getColumnModel().getColumn(1).setMinWidth(0);
        tblPackets.getColumnModel().getColumn(1).setMaxWidth(0);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jTextArea1.setFocusable(false);
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter Header"));

        jLabel2.setText("IP Address");

        jLabel3.setText("Port");

        jLabel5.setText("Host Name");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHostname, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIPAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIPAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter Data"));

        jLabel4.setText("String");

        tfString.setColumns(20);
        tfString.setRows(5);
        jScrollPane2.setViewportView(tfString);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    String ipAddress = "";
    String portAddress = "";
    String hostName = "";
    String _string = "";
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        if (btnStart.getText().equalsIgnoreCase("Start")) {
            if (cbNICOptions.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Chua chon NIC", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            captureState = true;
            cbNICOptions.setEnabled(false);
            btnStart.setText("Stop");
            ipAddress = txtIPAddress.getText().trim();
            portAddress = txtPort.getText().trim();
            hostName = txtHostname.getText().trim();
            _string = tfString.getText().trim();
            startSniffer();

        } else if (btnStart.getText().equals("Stop")) {
            cbNICOptions.setEnabled(true);
            captureState = false;
            btnStart.setText("Start");
            enableFilter();
        }

    }//GEN-LAST:event_btnStartActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        if (CAPTOR != null) {
            CAPTOR.close();
        }
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox cbNICOptions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tblPackets;
    private javax.swing.JTextArea tfString;
    private javax.swing.JTextField txtHostname;
    private javax.swing.JTextField txtIPAddress;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables

    private void fillNICOption() {
        listNI = JpcapCaptor.getDeviceList();
        for (int i = 0; i < listNI.length; i++) {
            cbNICOptions.addItem(listNI[i].description);
        }
    }

    private boolean stringContainsItemFromArray(String inputString, String[] array){
        for (int i = 0; i < array.length; i++) {
//            String string = array[i];
            if(inputString.contains(array[i])){
                return true;
            }
        }
        return false;
    }
    
    private void getPacketTextOnFrame(Packet packet) {
        //dtm.addRow(new Object[]{1,2,3,4,5});
        String[] argStr = _string.toLowerCase().split(";");
        
        try {
            //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //Date date = new Date();
            //String dateTimeOfPacket = dateFormat.format(date);
            IPPacket ipPacket = (IPPacket) packet;
            if (ipPacket.protocol == new Contants().TCP) {
                myTCPPacket = new MyTCPPacket(ipPacket);
                if (!_string.equals("")) {
                    if (stringContainsItemFromArray(myTCPPacket.getIsoDataOfPacket().toLowerCase(), argStr)) {
                        showResultTCP();
                    }
                } else {
                    showResultTCP();
                }
                System.out.println("----------------------------------------------------------------------");
            } else if (ipPacket.protocol == new Contants().UDP) {
                System.out.println("UDP");

            } else if (ipPacket.protocol == new Contants().ICMP) {
                System.out.println("ICMP");
                System.out.println("length:" + ipPacket.length);
                System.out.println("len:" + ipPacket.len);
                System.out.println("----------------------------------------------------------------------");
            } else {
                System.out.println("Unsupported Protocol " + ipPacket.protocol);
            }
        } catch (Exception e) {
            //System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    private void startSniffer() {
        // start a loop
        disableFilter();
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                //startSniffer();
                try {
                    int choice = cbNICOptions.getSelectedIndex() - 1;
                    CAPTOR = JpcapCaptor.openDevice(listNI[choice], 65535, false, 20);
                    //int i = 0;
                    //System.out.println(captureState);
                    while (captureState) {
                        // i = i+1;
                        //CAPTOR.processPacket(1, new PacketContents());
                        packet = CAPTOR.getPacket();

                        //System.out.println("-" + (i));
                        if ((packet != null) && (packet instanceof TCPPacket)) {
                            TCPPacket tcpP = (TCPPacket) packet;
                            if (!ipAddress.equals("")) {
                                if (!portAddress.equals("")) {
                                    if ((ipAddress.equals(tcpP.src_ip.getHostAddress()) && (portAddress.equals(String.valueOf(tcpP.dst_port))))) {
                                        getPacketTextOnFrame(packet);
                                    }
                                } else {
                                    if (ipAddress.equals(tcpP.src_ip.getHostAddress())) {
                                        getPacketTextOnFrame(packet);
                                    }
                                }
                            } else {
                                if (!portAddress.equals("")) {
                                    if (portAddress.equals(String.valueOf(tcpP.dst_port))) {
                                        getPacketTextOnFrame(packet);
                                    }
                                } else {
                                    getPacketTextOnFrame(packet);
                                }
                            }

//                            getPacketTextOnFrame(packet);
                        }
                    }
                    CAPTOR.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        ;
        };
            new Thread(runable).start();
    }

    private void disableFilter() {
        txtHostname.setEnabled(false);
        txtIPAddress.setEnabled(false);
        txtPort.setEnabled(false);
        tfString.setEnabled(false);
    }

    private void enableFilter() {
        txtHostname.setEnabled(true);
        txtIPAddress.setEnabled(true);
        txtPort.setEnabled(true);
        tfString.setEnabled(true);
    }

    private void showResultTCP() throws UnsupportedEncodingException, IOException {
        Vector vectorColumn = new Vector<>();
        //String isoData = new String(myTCPPacket.getDataOfTCPPacket(), "UTF-8");
        vectorColumn.add(myTCPPacket.getAckNumber());
        vectorColumn.add(myTCPPacket.getIdentNumber());
        vectorColumn.add(myTCPPacket.getDateTimeOfPacket());
        vectorColumn.add(myTCPPacket.getSourceIPOfTCPPacket());
        vectorColumn.add(myTCPPacket.getSourcePortOfTCPPacket());
        vectorColumn.add(myTCPPacket.getDestinationIPOfTCPPacket());
        vectorColumn.add(myTCPPacket.getDestinationPortOfTCPPacket());
        //System.out.println(isoData);
        System.out.println(new Utils().getHexString(myTCPPacket.getDataOfTCPPacket()));
        System.out.println("in showresult of myTCP: " + myTCPPacket.getIsoDataOfPacket());

        /* luu vao file */
        /*
         * thuat toan:
         * ack_num giong nhau => luu vao 1 file
         */
        if (myTCPPacket.getDataOfTCPPacket().length != 0) {
            String newFile = String.valueOf(myTCPPacket.getAckNumber()) + ".txt";
            boolean exitsFile = false;
            //liet ke cac file trong thu muc "result"
            File[] listOfFiles = folder.listFiles();
            for (File f : listOfFiles) {
                String filePath = f.toPath().getFileName().toString();
                //System.out.println(filePath);
                //tim xem co thu mua ackNum_seqNum.txt ko 
                if (filePath.contains(newFile)) {
                    System.out.println("file exits:" + newFile);
                    exitsFile = true;
                }
            }
            // neu tim duoc: exitsFile = true
            if (exitsFile) {
                //ghi append vao cuoi file
                FileWriter fwriter = new FileWriter(folder + "/" + newFile, true);
                //FileWriter fwriter = new FileWriter(newFile, true);
                try (BufferedWriter bw = new BufferedWriter(fwriter)) {
                    bw.write(myTCPPacket.getIsoDataOfPacket());
                }
            } else {
                try {
                    //ghi fie moi
                    new Utils().writeToFile(myTCPPacket.getIsoDataOfPacket(), folder + "/" + newFile);
                } catch (Exception ex) {
                    Logger.getLogger(Frame1.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            /* START kiem tra kiem thu */
            String testingBruteforce = myTCPPacket.testingBruteforce(bfBeanArray);
            String testingSQL = myTCPPacket.testingSQL(sqlBeanArray);
            String testingLapGoi = myTCPPacket.testLapGoi(ptBeanArray);
            String testingDoS = myTCPPacket.testingDoS(dosBeanArray);
            /* END kiem tra kiem thu */
            vectorColumn.add(testingSQL);
            vectorColumn.add(testingBruteforce);
            vectorColumn.add(testingLapGoi);
            vectorColumn.add(testingDoS);

            /*
             * 
             * them vao 18/08
             * 
             */

            dtm.addRow(vectorColumn);
        }
    }
}
