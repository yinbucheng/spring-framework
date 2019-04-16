package bucheng.yin.test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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
