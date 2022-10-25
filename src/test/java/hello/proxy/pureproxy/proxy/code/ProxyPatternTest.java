package hello.proxy.pureproxy.proxy.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    @Test
    @DisplayName("프록시 적용 전")
    void test1() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    @DisplayName("프록시 적용 후")
    void test2() {
        RealSubject realSubject = new RealSubject();
        CacheProxy proxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(proxy);
        client.execute();
        client.execute();
        client.execute();
    }
}
