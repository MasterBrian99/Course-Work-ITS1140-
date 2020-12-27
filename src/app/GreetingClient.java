package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class GreetingClient
{
    Image newimg;
    BufferedImage bimg;
    byte[] bytes;

    public static void main(String [] args)
    {
        String serverName = "localhost";
        int port = 6066;
        try
        {
            System.out.println("Connecting to " + serverName
                    + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());

            DataInputStream in=new DataInputStream(client.getInputStream());
            System.out.println(in.readUTF());
            System.out.println(in.readUTF());

            DataOutputStream out =
                    new DataOutputStream(client.getOutputStream());

            out.writeUTF("Hello from "
                    + client.getLocalSocketAddress());
            out.writeUTF("client: hello to server");

            ImageIcon img1=new ImageIcon("Ashish.jpg");
            Image img = img1.getImage();
            Image newimg = img.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);

            BufferedImage bimg = ImageIO.read(new File("../src/assets/images/backgroundLogin.png"));

            ImageIO.write(bimg,"JPG",client.getOutputStream());
            System.out.println("Image sent!!!!");
            client.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}