package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV2 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("write String : " + writeString);

        // String 인코딩을 OutputStreamWriter가 대신함
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8);
        osw.write(writeString);
        osw.close();

        // InputStreamReader가 byte 디코딩
        FileInputStream fis = new FileInputStream(FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, UTF_8);

        StringBuilder content = new StringBuilder();
        int ch; // EOF(-1)을 표현할 수 없어서 int로 반환
        while ((ch = isr.read()) != -1) {
            content.append((char)ch); // char로 변환
        }
        isr.close();

        System.out.println("read String = " + content);
    }

}
