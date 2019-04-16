package bucheng.yin.ioc.test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AopConfiguration
 * @Author buchengyin
 * @Date 2019/4/12 19:25
 **/
@Aspect
@Configuration
public class AopConfiguration {


	@Before("execution(* *..yin.test.*.*(..))")
	public void before(){
		System.out.println("--aop--before");
	}
}
