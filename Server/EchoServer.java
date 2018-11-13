
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luke Clements and Ryan Cole
 */
public class EchoServerServer 
{
    public static void main( String args[]) throws Exception 
    {
        DatagramSocket dsock;
        
        //sets up the byte array for the data and packet for the array of the specified size
        byte bytes[] = new byte[256];                                
        DatagramPacket dpack = new DatagramPacket(bytes, bytes.length );
        
        try
        {
            //sets up a UDP socket at the passed port number to listen for incoming data
            dsock = new DatagramSocket(Integer.parseInt(args[0]));

            while(true) 
            {
                //receives data
                dsock.receive(dpack);

                //gets data from the datagramPacket and converts it to a string
                byte messageBytes[] = dpack.getData();
                int packSize = dpack.getLength();
                String message = new String(messageBytes, 0, packSize);

                //prints the message received
                System.out.println(message);
                
                //sends the packet back to the sender
                dsock.send(dpack);
            }
        }
        catch(BindException be)
        {
            System.out.println("The selected port is already in use!");
        }
        catch(Exception e)
        {
            System.out.println("Port number invalid");
        }
    } 
}
