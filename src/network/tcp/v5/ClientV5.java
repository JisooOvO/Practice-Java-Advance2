package network.tcp.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ClientV5 {
    public static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        try(
            Socket socket = new Socket("localhost", PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {

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
        }
    }
}
