package VPLPluPlusCore.annotations;

import java.lang.annotation.Annotation;
import java.sql.Timestamp;

public class DefaultAnnotation implements VplTestDescriptorMethodAnnotation{

  private String name = new Timestamp(System.currentTimeMillis()).toString(); //default value
  public DefaultAnnotation(){
  }
  public DefaultAnnotation(String name){
    this.name = name;
  }

  @Override
  public String name(){
    return this.name;
  }
  @Override
  public String objective(){
    return "Undefined Objective";
  }
  @Override
  public int grade(){
    return 0;
  }
  @Override
  public String successMessage(){
    return "JUnit method test success";
  }
  @Override
  public String failureMessage(){
    return "JUnit method test fail";
  }
  @Override
  public String successReferenceLink(){
    return "";
  }
  @Override
  public String failureReferenceLink(){
    return "";
  }
  @Override
  public Class<? extends Annotation> annotationType(){
    return VplTestDescriptorMethodAnnotation.class;
  }
}
