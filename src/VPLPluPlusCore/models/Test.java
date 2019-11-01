/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import VPLPluPlusCore.Exceptions.VplTestException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import VPLPluPlusCore.annotations.VplTest;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class Test implements VplTest {

  private int approved = 0;
  private int maxGrade = 0;
  private final Class vplTestClass;
  private final VplTest annotation;
  private final HashMap<String, TestCase> descriptors;

  public Test(Class vplTestClass, VplTest annotation) {
    this.descriptors = new HashMap();
    this.annotation = annotation;
    this.vplTestClass = vplTestClass;

  }

  public static Test create(Class _class_) {
    VplTest annotation = (VplTest) _class_.getAnnotation(VplTest.class);
    return new Test(_class_, annotation);
  }

  /**
   * Method for add testDescriptor to VplTest
   *
   * @param testDescriptor testDescriptor to save
   * @throws VPLPluPlusCore.Exceptions.VplTestException
   */
  public void addTestCase(TestCase testDescriptor) throws VplTestException {

//valide if exist a method with same id
    String methodName = testDescriptor.getMethod().getName();

    if (!this.descriptors.containsKey(methodName)) {

      //if doesnt exist a method with same id then put it in hashmap
      this.descriptors.put(methodName, testDescriptor);
      this.maxGrade += testDescriptor.grade();

      this.setTestCaseSuccess(methodName);

    } else {
      //else throw the exception
      throw new VplTestException("Existen dos tests con el mismo nombre en la clase de prueba");
    }
  }

  public TestCase getMethodDescriptor(String methodName) {
    return this.descriptors.get(methodName);
  }

  public Class getVplTestClass() {
    return this.vplTestClass;
  }

  public void setTestCaseSuccess(String method) {
    this.getMethodDescriptor(method).setSuccess(true);
    this.approved += 1;
  }

  public void setTestCaseFailure(String method) {
    this.getMethodDescriptor(method).setSuccess(false);
    this.approved -= 1;
  }

  public int getTestCasesApprovedQuantity() {
    return this.approved;
  }

  public int getTestCasesFailed() {
    return this.getTestCasesQuantity() - this.approved;
  }

  public int getTestCasesQuantity() {
    return this.descriptors.size();
  }

  @Override
  public String project() {
    return this.annotation.project();
  }

  @Override
  public String name() {
   return this.annotation.name();
  }

  
  @Override
  public String created_by() {
    return this.annotation.created_by();
  }

  @Override
  public String objective() {
    return this.annotation.objective();
  }

  public int maxGrade() {
    return this.maxGrade;
  }

  @Override
  public Class<? extends Annotation> annotationType() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
