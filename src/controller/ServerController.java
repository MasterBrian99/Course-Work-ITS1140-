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


    public JFXTextArea txtMessageArea;
    public JFXTextField txtSendMessageArea;

    static ServerSocket serverSocket;
    static  Socket socket;
    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;

    public void initialize() throws IOException {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(5000);
                System.out.println("Server Stated");

                socket = serverSocket.accept();
                System.out.println("CLient Accecpted");
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                String messageIn = "";
                while (!messageIn.equals("exit")) {
                    messageIn = dataInputStream.readUTF();
                    txtMessageArea.appendText( messageIn.trim()+"\n" );

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


        public void sendOnAction(ActionEvent actionEvent) throws IOException {
            String reply="";
            reply=txtSendMessageArea.getText();
            dataOutputStream.writeUTF(reply);
            txtMessageArea.appendText("Server :"+reply+"\n");
    }
}