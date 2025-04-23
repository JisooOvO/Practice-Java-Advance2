package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class ClientV1 {
    public static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        // 클라이언트의 port는 아무거나 하나 할당
        Socket socket = new Socket("localhost", PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        log("소켓 연결 : " + socket);

        // 서버 <- 문자 보내기
        String toSend = "Hello";
        output.writeUTF(toSend);
        log("client -> server : " + toSend);

        // 서버 -> 문자 받기
        String received = input.readUTF();
        log("clinet <- server : " + received);

        // 자원 정리
        log("연결 종료 : " + socket);
        output.close();
        input.close();
        socket.close();
    }
}
