package com.study.tobyspring.config.autoconfig;

import java.util.Map;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import com.study.tobyspring.config.annotation.ConditionalMyOnClass;

public class MyOnClassCondition implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Map<String, Object> attrs = metadata.getAnnotationAttributes(ConditionalMyOnClass.class.getName());
		return ClassUtils.isPresent((String)attrs.get("value"), context.getClassLoader());
	}
}
