package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {

    public static void main(String[] args) throws IOException {
        unknownHostEx1();
        unknownHostEx2();

        connectionRefused();
    }

    private static void unknownHostEx1() throws IOException {
        try {
            // 이상한 ip
            Socket socket = new Socket("999.999.999.999", 80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void unknownHostEx2() throws IOException {
        try {
            // 이상한 Domain
            Socket socket = new Socket("google.gogo", 80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void connectionRefused() throws IOException {
        try {
            // * 연결 거절
            //   서버 컴퓨터에 접속했으나
            //    - 사용하는 PORT 없을 경우
            //    - 방화벽에서 연결을 막을 경우
            //
            // => Server OHOS TCP RST(Reset) 패킷을 보내 연결 거절
            //    클라이언트에서 이 패킷을 받으면 해당 예외 발생
            Socket socket = new Socket("localhost", 45678);
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }
}
