package network.exception.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SoTimeoutClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        InputStream input = socket.getInputStream();

        try {
            socket.setSoTimeout(3000); // socket timed out 시간 설정
            int read = input.read(); // read 대기 시간은 굉장히 길다
        } catch (Exception e) {
            e.printStackTrace();
        }

        socket.close();
    }

}
