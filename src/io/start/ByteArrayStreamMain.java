package io.start;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayStreamMain {

    public static void main(String[] args) throws IOException {
        byte[] input = {1,2,3};

        // 메모리 쓰기
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(input);
        bos.close();

        // 메모리 읽기
        ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
        byte[] bytes = bais.readAllBytes();
        System.out.println(Arrays.toString(bytes));
        bais.close();
    }

}
