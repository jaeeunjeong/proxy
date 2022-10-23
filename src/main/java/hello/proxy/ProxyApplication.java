package hello.proxy;

import hello.proxy.config.AppV1Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(AppV1Config.class)
// 주의 : 컴포넌트 스캔을 시작할 시작 위치를 정하는 것!
// -> 시작 위치를 비롯한 그 하위 위치가 아니라면 스프링 빈으로 등록되지 않음.
@SpringBootApplication(scanBasePackages = "hello.proxy.app")
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

}
