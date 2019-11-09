  package VPLPluPlusCore.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface VplTestCase {

  String id();
    
  String name() default "";

  String objective() default "";

  int grade() default 0;

  String successMessage() default "";

  String failureMessage() default "";

  String successReferenceLink() default "";

  String failureReferenceLink() default "";
}
