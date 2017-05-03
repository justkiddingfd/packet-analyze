/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1.ducpv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Admin
 */
public class Utils {
    /* 
     * Nhap vao so de kiem tra card mang nao duoc dung
     * 
     */

    public String getInput(String q) {
        String input = "";
        System.out.println(q);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = bf.readLine();
            return input;
        } catch (IOException ioexception) {
            return "";
        }

    }

    /* 
     * parse to  hex  (string) from byte[] 
     * 
     */
    public String getHexString(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        if (byteArray != null) {
            for (byte i : byteArray) {
                sb.append(Integer.toHexString(i & 0xff));
            }
            return sb.toString();
        }
        return "";
    }

    /* 
     * ghi du lieu vao file txt
     * 
     */
    public void writeToFile(String str, String file) throws Exception {
        File myFile = new File(file);
        // myFile.createNewFile();
        FileOutputStream fOut = new FileOutputStream(myFile);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        myOutWriter.write(str);
        myOutWriter.close();
        fOut.close();
    }
    
    /*
     * doc file
     */
    public String readFromFile(String file) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }
}
