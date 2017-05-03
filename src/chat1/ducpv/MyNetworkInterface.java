/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.ducpv;

import jpcap.NetworkInterface;

/**
 *
 * @author Admin
 */
public class MyNetworkInterface {
    static NetworkInterface[] listNI;
    static int x;
 
    public static void choiceNetworkInterface() {
        for (x = 0; x < listNI.length; x++) {
            System.out.println(x + " -> " + listNI[x].description);
            System.out.println();
        }
    }
}
