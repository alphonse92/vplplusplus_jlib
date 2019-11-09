package VPLPluPlusCore.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
public @interface VplTest {

  String project();
  
  String name() default "";

  String created_by() default "";

  String objective() default "";

}
