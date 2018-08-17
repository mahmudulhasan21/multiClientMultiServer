/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3workerserver;

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

public class Lab3WorkerServer {

    static int clientCount;
	
	
    public static void main(String[] args) throws InterruptedException {
        try {
            ServerSocket ss = new ServerSocket(8081);
	
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
                    dos.writeUTF("Working one");
                    System.out.print(j+" = ");  
                    System.out.print(k);
                    System.out.println();
                    sc.close();
                    //clientCount = clientCount + 1 ;
                }
                else 
                {
                    TimeUnit.SECONDS.sleep(4);
                    clientCount = 0;
                }
                
                /*
                DataInputStream dis;
                DataOutputStream dos;
		
                Socket sc = ss.accept();
                clientCount++;
		
                //Thread t = new Thread(new Service(sc));
                //t.start();
                dis = new DataInputStream(sc.getInputStream());
                dos = new DataOutputStream(sc.getOutputStream());
                
                int i,j,k;
                j = dis.readInt();
                k = j*j;
                TimeUnit.SECONDS.sleep(1);
                dos.writeInt(k);
                dos.writeUTF("Working one");
                System.out.print(j+" = ");  
                System.out.print(k);
                System.out.println();
                //TimeUnit.SECONDS.sleep(15);TimeUnit.SECONDS.sleep(1);TimeUnit.SECONDS.sleep(1);TimeUnit.SECONDS.sleep(1);TimeUnit.SECONDS.sleep(1);
		sc.close();

                */

            }
	
        } catch (IOException e) {
            System.out.println("All client gone");
        }
    }
   
}

