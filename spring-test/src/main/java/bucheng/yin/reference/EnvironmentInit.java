package bucheng.yin.reference;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author buchengyin
 * @Date 2019/4/14 9:20
 **/
@Component
public class EnvironmentInit implements EnvironmentAware{
	private ConfigurableEnvironment environment;


	@Override
	public void setEnvironment(Environment environment) {
		this.environment = (ConfigurableEnvironment) environment;
		Map<String,Object> data = new HashMap<>();
		data.put("name","yinchong");
		data.put("age",20);
		MapPropertySource mapSource = new MapPropertySource("test",data);
		this.environment.getPropertySources().addFirst(mapSource);
	}

}
