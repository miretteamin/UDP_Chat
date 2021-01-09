//
// Mirette Amin Danial
// ID: 20190570
// Group: S4
//

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            System.out.println("Client active");
            Scanner scan = new Scanner(System.in);

            InetAddress serverIP = InetAddress.getByName("localhost");

            System.out.println("You're ready to communicate\nTo close the connection write the word 'close' :)");
            while (true) {
                byte[] requestBytes = new byte[4096];
                byte[] responseBytes = new byte[4096];

                String input = scan.nextLine();

                if(input.equals("close"))
                {
                    System.out.println("Connection is closed");
                    clientSocket.close();
                    break;
                }
                requestBytes = input.getBytes();

                DatagramPacket clientPacket = new DatagramPacket(requestBytes, requestBytes.length, serverIP, 22000);
                clientSocket.send(clientPacket);

                DatagramPacket serverPacket = new DatagramPacket(responseBytes, responseBytes.length);
                clientSocket.receive(serverPacket);
                String response = new String(serverPacket.getData());
                System.out.println("Server: " + response);
            }
            clientSocket.close();
        } catch (IOException e){e.printStackTrace();}
    }
}
