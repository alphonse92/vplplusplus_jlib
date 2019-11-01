/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import VPLPluPlusCore.annotations.VplTestCase;

/**
 * Test case is a method of a VPL test
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class TestCase implements VplTestCase {

  private final VplTestCase annotation;
  private final Method method;
  private boolean success = true;

  public TestCase(Method method) {
    this.annotation = (VplTestCase) method.getAnnotation(VplTestCase.class);
    this.method = method;
  }

  @Override
  public String id() {
    return this.annotation.id();
  }

  @Override
  public String name() {
    return this.annotation.name();
  }

  @Override
  public String objective() {
    return this.annotation.objective();
  }

  @Override
  public int grade() {
    return this.annotation.grade();
  }

  @Override
  public String successMessage() {
    return this.annotation.successMessage();
  }

  @Override
  public String failureMessage() {
    return this.annotation.failureMessage();
  }

  @Override
  public String successReferenceLink() {
    return this.annotation.successReferenceLink();
  }

  @Override
  public String failureReferenceLink() {
    return this.annotation.failureReferenceLink();
  }

  @Override
  public Class<? extends Annotation> annotationType() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public Method getMethod() {
    return this.method;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean isSuccess) {
    this.success = isSuccess;
  }
  
}
