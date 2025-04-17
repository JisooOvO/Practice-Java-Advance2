package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain1 {

    private static final Charset EUC_KR = Charset.forName("euc-kr");
    private static final Charset MS_949 = Charset.forName("ms949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문 처리 ==");
        encoding("A", US_ASCII); // 65
        encoding("A", ISO_8859_1); // 65
        encoding("A", EUC_KR); // 65
        encoding("A", UTF_8); // 65
        encoding("A", UTF_16BE); // 0, 65
        encoding("A", MS_949); // 65

        System.out.println("== 한글 지원 ==");
        encoding("가", EUC_KR); // -80, -95
        encoding("가", MS_949); // -80, -95
        encoding("가", UTF_8); // -22, -80, -128
        encoding("가", UTF_16BE); // -84, 0
    }

    private static void encoding(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte\n", text, charset, Arrays.toString(bytes), bytes.length);
    }

}
