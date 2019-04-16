package bucheng.yin.ioc.aop;

import java.util.List;

/**
 * @ClassName AopCondition
 * @Author buchengyin
 * @Date 2019/4/12 19:52
 **/
public abstract class AopCondition {
	public boolean meet(Class clazz) {
		List<Class> available = available();
		for(Class temp:available){
			if(temp==clazz)
				return true;
		}
      return false;
	}

	abstract List<Class> available();
}
