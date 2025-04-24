package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결 : " + socket);

        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // client <- server : FIN
        Thread.sleep(1000); // 서버 close 대기

        // client -> server : PUSH[1]
        output.write(1); // FIN <-> ACK - FIN 이 규칙 그 외 RST

        Thread.sleep(1000); // RST 수신 대기
        // client <- server : RST
        try {
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace(); // SocketException : Connection reset
        }

        try {
            // RST 받고 또 보냄
            output.write(1);
        } catch (SocketException e) {
            e.printStackTrace(); // SocketException : Broken pipe
        }

        input.close();
        output.close();
        socket.close(); // 내 소켓을 닫았는데 input, ouput을 보냄

        input.read(); // SocketException Socket is closed

        // 비정상 종료 시나리오
        // Client <- FIN <- Server
        // Client -> ACK -> Server
        // Client -> PUSH(1) -> Server
        // Client <- RST <- Server (Connection Reset, Server down)
        // Client -> PUSH(1) -> Server (Broken pipe, OS SIGPIPE)

        // RST(Reset)
        // - TCP 스펙에 맞지 않은 순서로 메시지 전달
        // - TCP 버퍼에 있는 데이터를 다 읽지 않았는데 연결 종료 시
        // - 방화벽 등에서 강제 연결 종료
    }

}
