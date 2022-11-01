package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    /*
     * 리플렉션을 사용하는 이유 : 호출하는 메서드가 다른 공통된 로직이 존재한다.
     * */
    @DisplayName("리플렉션 추가 전")
    @Test
    void test1() {
        Hello target = new Hello();

        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);

        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result2);
    }

    /*
    * 코드가 갖고 있는 공통적인 성질,
    * 클래스나 메서드의 메타 정보를 사용해서 동적으로 호출하는 메서드를 변경한다.
    */
    @DisplayName("리플렉션 추가 1")
    @Test
    void test2() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1 = {}", result1);

        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2 = {}", result2);
    }

    @DisplayName("리플렉션 추가 2")
    @Test
    void test3() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    /**
     * 공톡로직 1, 공통로직 2를 한번에 처리할 수 있는 통합된 공통 처리 로직이다.
     */
    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target); // 호출할 메서드 정보를 넘겨서 메서드를 사용한다.
        log.info("result = {}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
