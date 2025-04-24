package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConnectTimeoutMain2 {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        try {
            // * 응답이 없을 경우
            Socket socket = new Socket(); // 객체만 만들면 바로 응답 안함
            socket.connect(
                new InetSocketAddress("192.168.1.250", 45678),
                3000 // Timeout 설정
            );
        } catch (SocketTimeoutException e) { // Timeout 설정 시
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("end = " + (end - start));
    }

}
