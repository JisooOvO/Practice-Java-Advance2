package network.tcp.autocloseable;

public class ResourceCloseMainV3 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CloseException, CallException {
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;
        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callEx();
        } catch (CallException e) {
            System.out.println("ex : " + e);
            throw e;
        } finally {
            // 나중에 생성한 것을 먼저 정리
            // 자원 정리도 예외를 잡아야 함
            if (resource2 != null) {
                try {
                    resource2.closeEx();
                } catch (CloseException e) {
                    System.out.println("close ex : " + e);
                }
            }

            if (resource1 != null) {
                try {
                    resource1.closeEx();
                } catch (CloseException e) {
                    System.out.println("close ex : " + e);
                }
            }
        }
    }

}
