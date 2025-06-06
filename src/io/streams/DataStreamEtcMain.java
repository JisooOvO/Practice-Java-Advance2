package io.streams;

import java.io.*;

public class DataStreamEtcMain {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/data.dat");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF("회원A");
        dos.writeInt(20); // 4byte int 그대로 저장
        dos.writeDouble(10.5); // 8byte
        dos.writeBoolean(true);
        dos.close();

        FileInputStream fis = new FileInputStream("temp/data.dat");
        DataInputStream dis = new DataInputStream(fis);

        // byte 개수가 다르므로 반드시 저장한 순서대로 읽어야함
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readDouble());
        System.out.println(dis.readBoolean());
        dis.close();
    }

}
