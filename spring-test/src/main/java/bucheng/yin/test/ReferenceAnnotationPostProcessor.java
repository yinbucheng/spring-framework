package bucheng.yin.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Ref;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author buchengyin
 * @Date 2019/4/14 8:32
 **/
public class ReferenceAnnotationPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements MergedBeanDefinitionPostProcessor {
	private ConcurrentHashMap<String, ReferenceAnnotationMetaData> cache = new ConcurrentHashMap<>();

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		ReferenceAnnotationMetaData referenceAnnotationMetaData = cache.get(beanName);
		if(referenceAnnotationMetaData!=null){
			referenceAnnotationMetaData.inject();
		}
		return null;
	}

	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		if (cache.get(beanName) != null) {
			return;
		}
		synchronized (beanType) {
			if (cache.get(beanName) == null) {
				ReferenceAnnotationMetaData annotationMetaData = ReferenceAnnotationMetaData.resolveAnnotation(beanType);
				cache.put(beanName, annotationMetaData);
			}
		}
	}

	static class ReferenceAnnotationMetaData {
		Class<?> target;
		List<ReferenceAnnotationFieldElement> fieldElements = new LinkedList<>();
		List<ReferenceAnnotationMethodElement> methodElements = new LinkedList<>();

		static ReferenceAnnotationMetaData resolveAnnotation(Class<?> clazz) {
			ReferenceAnnotationMetaData annotationMetaData = new ReferenceAnnotationMetaData();
			do {
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					if (Modifier.isStatic(field.getModifiers())) {
						continue;
					}
					Reference reference = field.getAnnotation(Reference.class);
					if (reference == null) {
						continue;
					}
					ReferenceAnnotationFieldElement element = new ReferenceAnnotationFieldElement();
					element.field = field;
					element.required = true;
					element.reference = reference;
					annotationMetaData.fieldElements.add(element);
				}

				Method[] methods = clazz.getDeclaredMethods();
				for (Method method : methods) {
					method.setAccessible(true);
					if (Modifier.isStatic(method.getModifiers()) || method.getParameterCount() == 0) {
						continue;
					}
					Reference reference = method.getAnnotation(Reference.class);
					if (reference == null) {
						continue;
					}
					ReferenceAnnotationMethodElement element = new ReferenceAnnotationMethodElement();
					element.method = method;
					element.reference = reference;
					element.required = true;
					annotationMetaData.methodElements.add(element);
				}
			} while (clazz != null || clazz != Object.class);
			return annotationMetaData;
		}

		public void inject() {

		}
	}

	static class ReferenceAnnotationFieldElement {
		Field field;
		boolean required;
		Reference reference;

	}

	static class ReferenceAnnotationMethodElement {
		Method method;
		boolean required;
		Reference reference;
	}
}
