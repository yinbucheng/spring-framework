package bucheng.yin.ioc;

import bucheng.yin.ioc.test.TestBean2;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @ClassName AopProxyTest
 * @Author buchengyin
 * @Date 2019/4/25 20:53
 **/
public class AopProxyTest {
	public static void main(String[] args) {
		TestBean2 testBean = new TestBean2();
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(testBean);
		proxyFactory.addAdvice(new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("------------------->before<------------------");
				return invocation.proceed();
			}
		});
		TestBean2 proxy =(TestBean2) proxyFactory.getProxy();
		proxy.test();
	}
}
