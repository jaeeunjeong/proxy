package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {
    @Override
    public String operation() {
        log.info("실제 로그 출력");
        sleep(1000);
        return "data";
    }

    private void sleep(int millis) {
        try {
            sleep(1000);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }
}
