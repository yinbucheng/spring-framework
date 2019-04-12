package bucheng.yin;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName TestApplicationContext
 * @Author buchengyin
 * @Date 2019/4/12 17:48
 **/
public class TestApplicationContext {
	public static void main(String[] args) {
		//测试ioc容器使用
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(TestConfiguration.class);
		applicationContext.refresh();
		TestBean bean = applicationContext.getBean(TestBean.class);
		bean.show();
	}
}
