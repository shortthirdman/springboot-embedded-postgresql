package com.shortthirdman.springboot;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Documented
@Inherited
@DataJpaTest
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY)
public @interface PostgresDataJpaTest {

    @AliasFor(annotation = DataJpaTest.class)
    boolean showSql() default true;

    @AliasFor(annotation = DataJpaTest.class)
    boolean useDefaultFilters() default true;

    @AliasFor(annotation = DataJpaTest.class)
    Filter[] includeFilters() default {};

    @AliasFor(annotation = DataJpaTest.class)
    Filter[] excludeFilters() default {};

    @AliasFor(annotation = DataJpaTest.class)
    Class<?>[] excludeAutoConfiguration() default {};
}
