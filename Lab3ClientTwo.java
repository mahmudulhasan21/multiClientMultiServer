/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3clienttwo;

/**
 *
 * @author user
 */



import java.net.*;
import java.io.*;
import java.util.*;

public class Lab3ClientTwo {

    public static void main(String[] args) {
        try {
            try (Socket sc = new Socket("localhost", 8080)) {
                DataInputStream dis = new DataInputStream(sc.getInputStream());
                DataOutputStream dos = new DataOutputStream(sc.getOutputStream());
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    
                    int i,j;
                    for(i =1;i<=50;i++)
                    {
                        dos.writeInt(i);
                        j = dis.readInt();
                        //String stChk = dis.readUTF();
                        System.out.print(i+" = "+j);
                        //System.out.print(j+ "From: "+stChk);
                        System.out.println();
                    }

                    System.out.println("Again ? Y/N");
                    String message = scanner.nextLine();
                    dos.writeUTF(message);
                    if (message.equalsIgnoreCase("N")) {
                        break;
                    }
                    
                }
                dis.close();
                dos.close();
            }
        } catch (IOException e) {
            System.out.println("Connection lost!!!");
        }
    }
}
