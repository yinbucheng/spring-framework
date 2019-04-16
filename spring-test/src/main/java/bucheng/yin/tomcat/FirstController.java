package bucheng.yin.tomcat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FirstController
 * @Author buchengyin
 * @Date 2019/4/16 16:20
 **/
@RestController
@RequestMapping("/first")
public class FirstController {

	@RequestMapping("/hello")
	public Object hello(){
		return "hello word";
	}
}
