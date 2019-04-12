package bucheng.yin.aop;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName BeanAopCreator
 * @Author buchengyin
 * @Date 2019/4/12 19:49
 **/
//@Component
public class BeanAopCreator implements BeanPostProcessor , BeanFactoryAware {
	private DefaultListableBeanFactory beanFactory;
	private List<AopCondition> aopConditions;
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(aopConditions==null) {
			synchronized (this) {
				String[] beanNamesForType = beanFactory.getBeanNamesForType(AopCondition.class);
				aopConditions = new LinkedList<>();
				for(String tempName:beanNamesForType){
					AopCondition aopCondition = beanFactory.getBean(tempName, AopCondition.class);
					aopConditions.add(aopCondition);
				}
			}
		}
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (DefaultListableBeanFactory) beanFactory;
	}

	class ProxyBean implements MethodInterceptor {

		private List<AopCondition> aopConditions = new LinkedList<>();

		public ProxyBean(List<AopCondition> aopConditions) {
			this.aopConditions = aopConditions;
		}

		@Override
		public Object intercept(Object object, Method method, Object[] objects, MethodProxy proxy) throws Throwable {

			return object;
		}

	}

}
