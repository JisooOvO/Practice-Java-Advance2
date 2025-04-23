package network.tcp.autocloseable;

public class ResourceCloseMainV4 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");

            Throwable[] suppressed = e.getSuppressed();
            for (Throwable throwable : suppressed) {
                System.out.println("suppressedEx : " + throwable);
            }

            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CloseException, CallException {
        try(
            // try-with-resource
            // - 나중에 선언한 것을 먼저 자동 정리 -> 리소스 누수 방지
            // - 자원 정리 이후에 Catch 호출
            // - 리소스 해제 중 발생한 부가 예외는 Suppressed Ex 처리
            // - 핵심 예외(Call Exception)을 던지게 됨
            ResourceV2 resource1 = new ResourceV2("resource1");
            ResourceV2 resource2 = new ResourceV2("resource2");
        ) {
            resource1.call();
            resource2.callEx(); // CallException
        } catch (CallException e) {
            System.out.println("ex : " + e);
            throw e;
        }
    }

}
