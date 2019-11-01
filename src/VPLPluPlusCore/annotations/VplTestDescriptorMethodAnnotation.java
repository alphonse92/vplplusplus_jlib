package VPLPluPlusCore.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface VplTestDescriptorMethodAnnotation {

  String id();
  
  String name();

  String objective();

  int grade();

  String successMessage() default "";

  String failureMessage() default "";

  String successReferenceLink() default "";

  String failureReferenceLink() default "";
}
