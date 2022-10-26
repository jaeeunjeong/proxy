package hello.proxy.pureproxy.proxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.TimeProxy;
import hello.proxy.pureproxy.proxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.proxy.concreteproxy.code.ConcreteLogic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {
    @Test
    @DisplayName("프록시 없는 버전")
    void test1() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }

    @Test
    @DisplayName("프록시 추가")
    void test2() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();
    }
}
