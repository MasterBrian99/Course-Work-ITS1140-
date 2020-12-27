package app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;

public class GreetingServer extends Thread
{
    private ServerSocket serverSocket;
    Socket server;

    public GreetingServer(int port) throws  Exception
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(180000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                server = serverSocket.accept();
                DataInputStream din=new DataInputStream(server.getInputStream());
                DataOutputStream dout=new DataOutputStream(server.getOutputStream());

                dout.writeUTF("server: -i am greeting server");
                dout.writeUTF("server:- hi! hello client");

                System.out.println(din.readUTF());
                System.out.println(din.readUTF());

                BufferedImage img= ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));

                System.out.println("Image received!!!!");
                //lblimg.setIcon(img);
            }
            catch(SocketTimeoutException st)
            {
                System.out.println("Socket timed out!");
                break;
            }
            catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
    }

    public static void main(String [] args) throws IOException, SQLException, ClassNotFoundException, Exception
    {
        //int port = Integer.parseInt(args[0]);

    }
}