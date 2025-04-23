package network.tcp.v4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.*;
import static util.MyLogger.log;

public class ClientV4 {
    public static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            socket = new Socket("localhost", PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            log("소켓 연결 : " + socket);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                // 서버 <- 문자 보내기
                System.out.print("전송 문자 : ");
                String toSend = scanner.nextLine();
                output.writeUTF(toSend);
                log("client -> server : " + toSend);

                // 서버 -> 문자 받기
                String received = input.readUTF();
                log("clinet <- server : " + received);

                if (toSend.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            log(e);
        } finally {
            closeAll(socket, input, output);
            log("연결 종료 : " + socket);
        }
    }
}
