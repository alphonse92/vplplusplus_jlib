package VPLPluPlusCore.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
public @interface VplTestInfoAnnotation {

  String name() default "";

  String[] tags();

  String created_by();

  String objetive();

  int maxGrade();
}
