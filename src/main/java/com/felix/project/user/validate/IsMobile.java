package com.felix.project.user.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = IsMobileValidator.class)
public @interface IsMobile {
    boolean needsValid() default true;
    Class<?>[] groups() default {};
    String message() default "手机格式不正确";
    Class<? extends Payload>[] payload() default{};
}
