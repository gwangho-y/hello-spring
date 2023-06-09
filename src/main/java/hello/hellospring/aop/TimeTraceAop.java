package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
//    @Around("execution(* hello.hellospring..*(..))")  // hello.hellospring 패키지 하위에는 다 적용시킨다는 뜻
    @Around("execution(* hello.hellospring.service..*(..))") // // hello.hellospring.service 패키지 하위에는 다 적용시킨다는 뜻
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START = " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END =  " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}
