package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private  static final String SERVER_IP="127.0.01";
    private static final int SERVER_PORT=5000;
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket(SERVER_IP,SERVER_PORT);

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String serverResponse=bufferedReader.readLine();
        System.out.println(serverResponse);


        socket.close();
        System.exit(0);
    }

}
