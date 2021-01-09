//
// Mirette Amin Danial
// ID: 20190570
// Group: S4
//

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws SocketException {
        try {

            DatagramSocket serverSocket = new DatagramSocket(22000);
            System.out.println("Server is up");
            Scanner scan = new Scanner(System.in);

            while (true) {

                byte[] requestBytes = new byte[4096];
                byte[] responseBytes = new byte[4096];

                // create a client packet for receiving the client's request
                DatagramPacket clientPacket = new DatagramPacket(requestBytes, requestBytes.length);
                // Receive client's request onto client packet
                serverSocket.receive(clientPacket);
                String req = new String(clientPacket.getData());
                System.out.println("Client: " + req);

                String input = scan.nextLine();
                responseBytes = input.getBytes();

                //Extract IP address and port number of client
                InetAddress clientIP = clientPacket.getAddress();
                int clientPort = clientPacket.getPort();



                //create a server packet to send a response to client
                DatagramPacket serverPacket = new DatagramPacket(responseBytes, responseBytes.length, clientIP, clientPort);

                //Send server's response to the client
                serverSocket.send(serverPacket);
            }

        }catch (IOException e){e.printStackTrace();}

    }
}
