/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3workerservertwo;

/**
 *
 * @author user
 */

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lab3WorkerServerTwo {
    
    static int clientCount;
    
    public static void main(String[] args) throws InterruptedException {
        try {
            ServerSocket ss = new ServerSocket(8082);
            clientCount = 0;
	
            while (true) {
                
                if(clientCount <10)
                {
                    DataInputStream dis;
                    DataOutputStream dos;

                    Socket sc = ss.accept();
                    clientCount = clientCount + 1 ;
                    dis = new DataInputStream(sc.getInputStream());
                    dos = new DataOutputStream(sc.getOutputStream());

                    int i,j,k;
                    j = dis.readInt();
                    k = j*j;
                    //k = 4444;
                    TimeUnit.SECONDS.sleep(1);
                    dos.writeInt(k);
                    dos.writeUTF("Working two");
                    System.out.print(j+" = ");  
                    System.out.print(k);
                    System.out.println();
                    sc.close();
                    
                }
                else 
                {
                    TimeUnit.SECONDS.sleep(4);
                    clientCount = 0;
                }
                
                /*
                ////////
                DataInputStream dis;
                DataOutputStream dos;
		
                Socket sc = ss.accept();
		
                dis = new DataInputStream(sc.getInputStream());
                dos = new DataOutputStream(sc.getOutputStream());
                
                int i,j,k;
                j = dis.readInt();
                k = j*j;
                //k = 4444;
                TimeUnit.SECONDS.sleep(1);
                dos.writeInt(k);
                dos.writeUTF("Working two");
                System.out.print(j+" = ");  
                System.out.print(k);
                System.out.println();
		sc.close();
                ////
                */
            }
	
        } catch (IOException e) {
            System.out.println("All client gone");
        }
    }

    
}
