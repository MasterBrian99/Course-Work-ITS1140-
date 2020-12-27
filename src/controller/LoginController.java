package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LoginController {
    public JFXTextField txtUsername;

    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    public AnchorPane LoginPanel;
    public AnchorPane MainPanel;
    public JFXTextArea txtAllMessages;
    public TextField txtSedMessages;
    public Button btnSendMessage;
    String name="";


    public void initialize(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket =new Socket("localhost",5000);
                    dataInputStream =new DataInputStream(socket.getInputStream());
                    dataOutputStream=new DataOutputStream(socket.getOutputStream());

                    String messageIn="";

                    while (!messageIn.equals("end")){
                        messageIn=dataInputStream.readUTF();
                        txtUsername.appendText(name+" "+messageIn.trim());
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


    public void loginOnAction(ActionEvent actionEvent) {
        LoginPanel.setVisible(false);
        MainPanel.setVisible(true);
        try {

            name=txtUsername.getText();
            String mess=name+" just hop in to the server \n";
            dataOutputStream.writeUTF(mess);
            txtAllMessages.appendText(mess);



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void sendBtnOnAction(ActionEvent actionEvent) throws IOException {
        String se=txtSedMessages.getText();
        dataOutputStream.writeUTF(name+" : "+ se+"\n");
        txtAllMessages.appendText(name+" : "+ se+"\n");


    }
}
