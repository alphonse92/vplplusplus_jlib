/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import VPLPluPlusCore.annotations.DefaultAnnotation;
import java.lang.reflect.Method;
import VPLPluPlusCore.annotations.VplTestDescriptorMethodAnnotation;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplTestMethodDescriptor {

  private VplTestDescriptorMethodAnnotation annotation;
  private Method method;
  private boolean success;

  public VplTestMethodDescriptor(Method method) {
    this.annotation = (VplTestDescriptorMethodAnnotation) method.getAnnotation(VplTestDescriptorMethodAnnotation.class);
    if (this.annotation == null) {
      this.annotation = new DefaultAnnotation(method.getName());
    }
    this.method = method;
  }

  public int getGrade() {
    return annotation.grade();
  }

  public Method getMethod() {
    return this.method;
  }

  public String getName() {
    return annotation.name();
  }

  public String getObjetive() {
    return annotation.objective();
  }

  public String getSuccessMessage() {
    return annotation.successMessage();
  }

  public String getFailureMessage() {
    return annotation.failureMessage();
  }

  public String getSuccessReferenceLink() {
    return annotation.successReferenceLink();
  }

  public String getFailureReferenceLink() {
    return annotation.failureReferenceLink();
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean isSuccess) {
    this.success = isSuccess;
  }

}
