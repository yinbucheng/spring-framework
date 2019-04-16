package bucheng.yin.test;

import bucheng.yin.ioc.reference.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestBean
 * @Author buchengyin
 * @Date 2019/4/12 18:42
 **/
@Component("testBean")
@Lazy
public class TestBean {
	@Autowired
	private TestBean2 testBean2;
	@Reference("yinchong_test")
	private String name;
	public void show(){
		System.out.println("------->show<--------"+name);
		testBean2.test();
	}
}
