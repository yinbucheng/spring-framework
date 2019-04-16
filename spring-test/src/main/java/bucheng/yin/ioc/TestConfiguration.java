package bucheng.yin.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName TestConfiguration
 * @Author buchengyin
 * @Date 2019/4/12 18:41
 **/
@Configuration
@ComponentScan(basePackages = "bucheng.yin.ioc")
@Import(BeanDefinationRegistrar.class)
public class TestConfiguration {
}
