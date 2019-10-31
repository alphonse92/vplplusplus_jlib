/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import VPLPluPlusCore.Exceptions.VplTestException;
import VPLPluPlusCore.annotations.VplTestInfoAnnotation;
import java.util.HashMap;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplTest {

  private final String[] tags;
  private final String created_by;
  private final String objetive;
  private int maxGrade = 0;
  private final String name;
  private double grade;
  private final HashMap<String, VplTestMethodDescriptor> descriptors;
  private final Class vplTestClass;

  private int approved = 0;

  public VplTest(Class vplTestClass, String name, String[] tags, String created_by, String objetive) {
    this.name = name;
    this.tags = tags;
    this.created_by = created_by;
    this.objetive = objetive;
    this.descriptors = new HashMap();
    this.vplTestClass = vplTestClass;
  }

  public static VplTest create(Class _class_) {
    VplTestInfoAnnotation annotation = (VplTestInfoAnnotation) _class_.getAnnotation(VplTestInfoAnnotation.class);
    return new VplTest(_class_, annotation.name(), annotation.tags(), annotation.created_by(), annotation.objetive());
  }

  /**
   * Method for add testDescriptor to VplTest
   *
   * @param testDescriptor testDescriptor to save
   * @throws VPLPluPlusCore.Exceptions.VplTestException
   */
  public void addTestMethodDescriptor(VplTestMethodDescriptor testDescriptor) throws VplTestException {

//valide if exist a method with same id
    String methodName = testDescriptor.getMethod().getName();

    if (!this.descriptors.containsKey(methodName)) {

      //if doesnt exist a method with same id then put it in hashmap
      this.descriptors.put(methodName, testDescriptor);
      this.maxGrade += testDescriptor.getGrade();

      this.setSuccess(methodName);

    } else {
      //else throw the exception
      throw new VplTestException("Existen dos tests con el mismo nombre en la clase de prueba");
    }
  }

  public VplTestMethodDescriptor getMethodDescriptor(String methodName) {
    return this.descriptors.get(methodName);
  }

  public Class getVplTestClass() {
    return this.vplTestClass;
  }

  public String[] getTags() {
    return tags;
  }

  public String getCreated_by() {
    return created_by;
  }

  public String getObjetive() {
    return objetive;
  }

  public int getMaxGrade() {
    return maxGrade;
  }

  public String getName() {
    return name;
  }

  public double getGrade() {
    return grade;
  }

  public void setGrade(double grade) {
    this.grade = grade;
  }

  public void setSuccess(String method) {
    this.getMethodDescriptor(method).setSuccess(true);
    this.approved += 1;
  }

  public void setFailure(String method) {
    this.getMethodDescriptor(method).setSuccess(false);
    this.approved -= 1;
  }

  public int getApproved() {
    return this.approved;
  }

  public int getNotApprove() {
    return this.getTestDescriptorsSize() - this.approved;
  }

  public int getTestDescriptorsSize() {
    return this.descriptors.size();
  }

}
