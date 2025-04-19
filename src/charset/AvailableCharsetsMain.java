package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;
import java.lang.*;

public class AvailableCharsetsMain {

    public static void main(String[] args) {
        // 이용 가능한 모든 Charset 자바 + OS
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (String charsetName : charsets.keySet()) {
            System.out.println("charsetName = " + charsetName);
        }

        System.out.println("====");

        // 문자로 조회 (대소문자 구분X)
        Charset ms949 = Charset.forName("MS949");
        System.out.println("ms949 = " + ms949);

        // 별칭 조회
        Set<String> aliases = ms949.aliases();
        for (String alias : aliases) {
            System.out.println("alias = " + alias);
        }

        // UTF-8 문자로 조회
        Charset utf8 = Charset.forName("utf-8");
        System.out.println("utf8 = " + utf8);

        // UTF-8 상수로 조회
        Charset utf8c = StandardCharsets.UTF_8;
        System.out.println("utf8c = " + utf8c);

        // 시스템의 기본 Charset 조회
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("defaultCharset = " + defaultCharset);
    }

}
