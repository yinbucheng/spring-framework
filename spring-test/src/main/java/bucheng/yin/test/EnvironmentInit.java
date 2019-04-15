package bucheng.yin.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author buchengyin
 * @Date 2019/4/14 9:20
 **/
public class EnvironmentInit implements EnvironmentAware, InitializingBean, ApplicationListener<> {
	private ConfigurableEnvironment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String,Object> data = new HashMap<>();
		data.put("name","yinchong");
		data.put("age",20);
		MapPropertySource mapSource = new MapPropertySource("test",data);
		environment.getPropertySources().addFirst(mapSource);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = (ConfigurableEnvironment) environment;
	}
}
