package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class ConnectTimeoutMain1 {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        try {
            // * 응답이 없을 경우
            //   - ip를 사용하는 서버가 없을 경우
            //   - TCP 연결시 os 기본 연결 대기 타임아웃 존재 Operation timed out
            //     (Windows - 약 21초, Linux, Mac : 약 75 초)
            Socket socket = new Socket("192.168.1.250", 45678);
        } catch (ConnectException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("end = " + (end - start));
    }

}
