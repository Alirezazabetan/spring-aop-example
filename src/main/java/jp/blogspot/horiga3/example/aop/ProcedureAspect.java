package jp.blogspot.horiga3.example.aop;

import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProcedureAspect {

	static Logger logger = LoggerFactory.getLogger(ProcedureAspect.class);

	@Pointcut("execution(* jp.blogspot.horiga3.*.*(..)) ")
	public void targetMethods() {}
	
	@Before("@annotation(jp.blogspot.horiga3.example.aop.Procedure)")
	public void preHandle() {
		logger.info("Aspect :: preHandle");
	}
	
	@AfterReturning(
			pointcut="@annotation(jp.blogspot.horiga3.example.aop.Procedure)",
			returning="retVal")
	public void postHandle(Object retVal) {
		logger.info("Aspect :: postHandle, retVal={}", retVal != null ? retVal.toString() : "null");
	}
	
	@Around("@annotation(jp.blogspot.horiga3.example.aop.Procedure)")
	public Object handle(ProceedingJoinPoint pjp) {

		logger.info("Aspect :: around - start");

		Object[] args;
		try {
			args = pjp.getArgs();
			return args == null ? pjp.proceed() : pjp.proceed(args);
		} catch (Throwable e) {
			logger.info("Aspect :: handleException");
			int statusCode = 500;
			String statusMessage = "unknown";
			if (e instanceof ProcedureException) {
				statusCode = ((ProcedureException) e).getStatusCode();
				statusMessage = ((ProcedureException) e).getStatusMessage();
			} else if (e instanceof IllegalArgumentException) {
				statusCode = 400;
				statusMessage = "Invalid parameter";
			}
			Output<Object> error = new Output<Object>(UUID.randomUUID().toString(), statusCode, statusMessage);
			return error;
		} finally {
			logger.info("Aspect :: around - end");
		}
	}
}
