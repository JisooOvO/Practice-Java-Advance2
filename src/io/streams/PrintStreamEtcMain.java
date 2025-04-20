package io.streams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamEtcMain {

    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("temp/print.txt");

        // 입력을 콘솔에 출력하듯 파일에 저장 가능
        // PrintStream printStream = new PrintStream(System.out);
        PrintStream printStream = new PrintStream(fos);

        // 전부 문자로 변경하여 인코딩
        printStream.println("hello java!");
        printStream.println("10");
        printStream.println(true);
        printStream.printf("hello %s", "world");

        printStream.close();
    }

}
