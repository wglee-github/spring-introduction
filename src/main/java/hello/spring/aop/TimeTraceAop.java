package hello.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect 
public class TimeTraceAop {

	/**
	 *  
	 	*  AOP Class를 bean에 등록하는 방법
			1. Class에 @Component 를 선언한다.
		   	2. spring @Configuration 파일에 직접 bean을 등록한다. 
	   
	   		* 2번 방법으로 등록 시 순환참조 문제 발생할 수 있음.
	   		
	   			· 원인
				   · @Around 에서 AOP 적용 범위를 지정하는데[execution(* hello.spring..*(..))] 이때 지정한 범위 안에 @Configuration 파일이 포함되어 있으면 문제가 발생한다.
				   · config 파일의 bean 등록을 위해 선언한 메소드(TimeTraceAop)도 AOP를 처리를 하게 되면서 순환참조 문제가 발생한다.
			   		@Bean
					public TimeTraceAop timeTraceAop() {
						return new TimeTraceAop();
					}
			
				· 해결방법	
					1. 해당 config 파일을 AOP 대상에서 제외해 준다.
						· @Around("execution(* hello.spring..*(..)) && !target(hello.spring.config.SpringConfig))")
					2. config 파일의 직접 bean 등록한 부분을 삭제하고, AOP Class에 @Component를 선언한다.
	 *  
	 */
	@Around("execution(* hello.spring..*(..)) && !target(hello.spring.config.SpringConfig))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.currentTimeMillis();
		
		System.out.println("START : " + joinPoint.toString());
		
		try {
			return joinPoint.proceed();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			
			System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
		}
	}
}
