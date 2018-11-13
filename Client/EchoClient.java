
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luke Clements and Ryan Cole
 */
public class EchoServerClient 
{
    public static void main( String args[] ) throws Exception 
    {   
        InetAddress address;
        DatagramSocket dsock;
        int portNumber;
        String messageSent;
        String messageRec;
        
        Scanner in = new Scanner(System.in);

        try
        {
            //gets the name of the host server from passed args parameter
            address = InetAddress.getByName(args[0]);
            //gets the port number to be used on the host server from passed args parameter
            portNumber = Integer.parseInt(args[1]);
            
            while(true) {
                //prompts for and receives user message to be sent to the server
                System.out.print("What message would you like to send(enter !wq to exit): ");
                messageSent = in.nextLine();

                //will exit if the message is the exit code
                if(messageSent.equals("!wq")) {
                    break;
                }

                //sets up the socket with UDP protocol
                dsock = new DatagramSocket();

                //converts message into byte array for UDP protocol and sets up the packet to be sent
                byte bytes[] = messageSent.getBytes( );  
                DatagramPacket dpack = new DatagramPacket(bytes, bytes.length, address, portNumber);
                
                //sends the packet
                dsock.send(dpack);

                //receives a response packet and prints that message
                dsock.receive(dpack);
                messageRec = new String(dpack.getData( ));
                System.out.println(messageRec);
            }
        }
        catch(ArrayIndexOutOfBoundsException ae)
        {
            System.out.println("Missing hostname or port number");
        }
        catch(Exception e)
        {
            System.out.println("Non-integer port number");
        }
    }
}
