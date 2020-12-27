package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    public Label lblMessage;
    public JFXTextArea txtMessage;

    String massageIn="";

    public void initialize(){
        new Thread(()->{
            try {
                serverSocket =new ServerSocket(5000);
                System.out.println("Server Stated");
                socket=serverSocket.accept();

                System.out.println("CLient Accecpted");

                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());



                while(!massageIn.equals("end")){
                    massageIn=dataInputStream.readUTF();
                    txtMessage.appendText("\n "+massageIn.trim());
                    dataOutputStream.writeUTF(massageIn.trim());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }






    public void sendMassage(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtMessage.getText().trim());



    }
}
