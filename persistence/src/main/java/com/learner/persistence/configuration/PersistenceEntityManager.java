package com.learner.persistence.configuration;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.TYPE, ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
public @interface PersistenceEntityManager {
}
