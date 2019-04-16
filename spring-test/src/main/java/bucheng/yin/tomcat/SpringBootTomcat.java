package bucheng.yin.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @ClassName SpringBootTomcat
 * @Author buchengyin
 * @Date 2019/4/16 16:15
 **/
public class SpringBootTomcat {
	private static int PORT = 9090;
	private static String CONTEXT_PATH="/tomcat";
	private static String SERVLET_NAME = "dispatchServlet";

	public static void main(String[] args) throws LifecycleException {

		//创建tomcat服务器
		Tomcat tomcatServer = new Tomcat();
		//设置端口号
		tomcatServer.setPort(PORT);
		//是否自动部署
		tomcatServer.getHost().setAutoDeploy(false);

		//创建上下文
		StandardContext standardContext = new StandardContext();
		standardContext.setPath(CONTEXT_PATH);
		standardContext.addLifecycleListener(new Tomcat.FixContextListener());

		//将上下文加入到tomcat中去
		tomcatServer.getHost().addChild(standardContext);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ClassScannConfiguration.class);
		context.refresh();
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		dispatcherServlet.setApplicationContext(context);
		//创建Servlet
		tomcatServer.addServlet(CONTEXT_PATH,SERVLET_NAME,dispatcherServlet);
		// servleturl映射
		standardContext.addServletMappingDecoded("/", SERVLET_NAME);
		tomcatServer.start();

		System.out.println("tomcat服务器启动成功..");
		//异步接收请求
		tomcatServer.getServer().await();
	}
}
