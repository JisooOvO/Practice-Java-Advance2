package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV1 {

    public static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");

        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트 : " + PORT);

        // ServerSocket - client TCP 연결만 지원
        // OS backlog queue 보관 [클라이언트, 서버 IP/Host 저장]
        // accept -> backlog queue에서 TCP 연결 정보를 조회 (블로킹)
        // queue의 정보를 기반으로 socket 생성 -> queue에서 제거
        Socket socket = serverSocket.accept();
        log("소켓 연결 : " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        // 클라이언트 -> 문자 받기
        String received = input.readUTF();
        log("client -> server : " + received);

        // 클라이언트 <- 문자 보내기
        String toSend = received + " World!";
        output.writeUTF(toSend);
        log("client <- server : " + toSend);

        log("연결 종료 : " + socket);
        output.close();
        input.close();
        socket.close();
        serverSocket.close();
    }

}
