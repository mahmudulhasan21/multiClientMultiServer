/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3servernext;

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



public class Lab3ServerNext {

    static int clientCount;
    static int bufferCount = 0;
    static int[] mainServerBuffer = new int [50];
    static int workingServerOne = 0;
    static int workingServerTwo = 0;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);
	
            clientCount = 0;
            while (true) {
		
                Socket sc = ss.accept();
                clientCount++;
		
                Thread t = new Thread(new Service(sc,clientCount));
                t.start();
		
            }
	
        } catch (IOException e) {
            System.out.println("All client gone");
        }
    }

    
}



class Service implements Runnable {

    private Socket sc;
    private DataInputStream dis;
    private DataOutputStream dos;
    Socket sckt;
    DataInputStream disSckt;
    DataOutputStream dosSckt;
    int clientCount; int connection = 0;

    public Service(Socket s,int c) throws IOException {
        
        try {
            //sckt = new Socket("localhost", 8081);
            clientCount = c;
            if(clientCount % 2 == 0)
            {
                connection = 0;
            }
            else
            {
                connection = 1;
            }
            sc = s;
            dis = new DataInputStream(sc.getInputStream());
            dos = new DataOutputStream(sc.getOutputStream());
            //disSckt = new DataInputStream(sckt.getInputStream());
            //dosSckt = new DataOutputStream(sckt.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error (In constructor of serviceprovider): " + ex);
        }
    }
    
    

    @Override
    public void run() {
        
        while (true) {
            try {
                int i,j,k;
                for(i =0;i<50;i++)
                {
                    /*
                    if(connection == 0)
                    {
                        sckt = new Socket("localhost", 8081);
                        connection = 1;
                    }
                    else if(connection == 1)
                    {
                        sckt = new Socket("localhost", 8082);
                        connection = 0;
                    }
                    */
                    
                    if(Lab3ServerNext.workingServerOne <= Lab3ServerNext.workingServerTwo)
                    {
                        sckt = new Socket("localhost", 8081);
                        Lab3ServerNext.workingServerOne = Lab3ServerNext.workingServerOne + 1 ;
                        System.out.println("ONE : " + Lab3ServerNext.workingServerOne);
                    }
                    else 
                    {
                        sckt = new Socket("localhost", 8082);
                        Lab3ServerNext.workingServerTwo = Lab3ServerNext.workingServerTwo + 1 ;
                        System.out.println("TWO : " + Lab3ServerNext.workingServerTwo);
                    }
                    
                    //sckt = new Socket("localhost", 8081);
                    disSckt = new DataInputStream(sckt.getInputStream());
                    dosSckt = new DataOutputStream(sckt.getOutputStream());
                    try {
                        j = dis.readInt();
                        if (Lab3ServerNext.bufferCount == 50)
                        {
                            Lab3ServerNext.bufferCount = 0;
                        }
                        Lab3ServerNext.mainServerBuffer[Lab3ServerNext.bufferCount] = j;
                        dosSckt.writeInt(Lab3ServerNext.mainServerBuffer[Lab3ServerNext.bufferCount]);
                        Lab3ServerNext.bufferCount = Lab3ServerNext.bufferCount + 1;
                        //k = j*j;
                        //dosSckt.write(j);
                        k = disSckt.readInt();
                        dos.writeInt(k);
                        String stChk = disSckt.readUTF();
                        //dos.writeUTF(stChk);
                        System.out.print(j+" = ");
                        System.out.print(k + "From: "+stChk);System.out.println();
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }   String message = dis.readUTF();
                if (message.equalsIgnoreCase("N"))
                {
                    break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
