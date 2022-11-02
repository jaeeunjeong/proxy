package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceBasicHandler implements InvocationHandler {

    private final Object target; // 프록시가 호출할 대상.
    private final LogTrace logTrace;

    // JDK 동적 프록시에서 사용된다.
    public LogTraceBasicHandler(Object target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        TraceStatus status = null;
        try {

            // 메소드를 이용해서 호출되는 메서드 정보와 클래스 정보를 동적으로 확인할 수 있다.
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(message);

            Object result = method.invoke(target, args);

            logTrace.end(status);

            return result;

        } catch (Exception e) {

            logTrace.exception(status, e);

            throw e;
        }
    }
}
