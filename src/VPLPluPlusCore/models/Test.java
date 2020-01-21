/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import VPLPluPlusCore.Exceptions.VplTestException;
import VPLPluPlusCore.annotations.VplPlusPlus;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import VPLPluPlusCore.annotations.VplTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class Test implements VplTest {

  private int approved = 0;
  private int maxGrade = 0;
  private int gradeLost = 0;

  private boolean evaluateByAvg = false;

  private Class vplTestClass = null;
  private VplTest annotation = null;
  private HashMap<String, TestCase> testCases = new HashMap();
  private boolean is_compilation_error = false;

  public static String NO_PROJECT_SETTED="PROJECT_ID_WAS_NOT_FOUND";
  
  public Test(Class vplTestClass, VplTest annotation) {
    this.annotation = annotation;
    this.vplTestClass = vplTestClass;
  }

  public Test() {
  }

  public ArrayList<TestCase> getArrayOfTestCases(){
    Collection<TestCase> values = this.testCases.values(); 
    return new ArrayList<TestCase>(values);
  }
  
  public boolean isNoCompiled() {
    return this.is_compilation_error;
  }

  public void setNoCompiled(boolean val) {
    this.is_compilation_error = val;
  }

  public static Test create(Class _class_) {
    VplTest annotation = (VplTest) _class_.getAnnotation(VplTest.class);
    return new Test(_class_, annotation);
  }

  /**
   * Method for add testDescriptor to VplTest
   *
   * @param testcase testDescriptor to save
   * @throws VPLPluPlusCore.Exceptions.VplTestException
   */
  public void addTestCase(TestCase testcase) throws VplTestException {

    //valide if exist a method with same id
    String methodName = testcase.getMethod().getName();
    int grade = testcase.grade();

    // if grade is setted
    if (grade == 0) {
      this.evaluateByAvg = true;
    }

    if (!this.testCases.containsKey(methodName)) {

      //if doesnt exist a method with same id then put it in hashmap
      this.maxGrade += grade;
      this.testCases.put(methodName, testcase);
      this.setTestCaseSuccess(methodName);

    } else {
      //else throw the exception
      throw new VplTestException("Existen dos tests con el mismo nombre en la clase de prueba");
    }
  }

  private TestCase getTestCase(String methodName) {
    return this.testCases.get(methodName);
  }

  public Class getVplTestClass() {
    return this.vplTestClass;
  }

  public void setTestCaseSuccess(String method) {
    this.getTestCase(method).setSuccess(true);
    this.approved += 1;
  }

  public void setTestCaseFailure(String method) {

    TestCase testCase = this.getTestCase(method);

    if (testCase != null) {
      this.approved -= 1;
      this.gradeLost += testCase.grade();
      testCase.setSuccess(false);
    }
  }

  public int getTestCasesApprovedQuantity() {
    return this.approved;
  }

  public int getTestCasesFailed() {
    return this.getTestCasesQuantity() - this.approved;
  }

  public int getTestCasesQuantity() {
    return this.testCases.size();
  }

  public boolean shouldEvaluateByAvg() {
    return this.evaluateByAvg;
  }

  @Override
  public String project() {
    return this.annotation.project();
  }

  public String getProjectId(String project_id) throws VplTestException {
    if(project_id !=null) return project_id;
    
    if(this.annotation == null) throw new VplTestException(Test.NO_PROJECT_SETTED);
    if(this.project() == null) throw new VplTestException(Test.NO_PROJECT_SETTED);
    
    return this.project();
    
  }

  @Override
  public String name() {
    return this.vplTestClass.getName();
  }

  @Override
  public String created_by() {
    return this.annotation.created_by();
  }

  @Override
  public String objective() {
    return this.annotation.objective();
  }

  public int getGradeLost() {
    return this.gradeLost;
  }

  public int getMaxGrade() {
    return this.maxGrade;
  }

  @Override
  public Class<? extends Annotation> annotationType() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  private ArrayList<String> getJsonOfTestCases() {
    ArrayList<String> jsonOfTestCases = new ArrayList();

    for (Map.Entry pair : this.testCases.entrySet()) {
      TestCase testCase = (TestCase) pair.getValue();
      jsonOfTestCases.add(testCase.toJson());
    }

    return jsonOfTestCases;
  }

  public static boolean isTheClassAVplTest(Class classLoadedFromExecutionFile) {
    return classLoadedFromExecutionFile
            .isAnnotationPresent(VplPlusPlus.class)
            && ((VplPlusPlus) classLoadedFromExecutionFile
                    .getAnnotation(VplPlusPlus.class))
                    .enabled();
  }

  private String getJson(String moodle_user, String project_id) throws VplTestException {
    String project = this.getProjectId(project_id);
    ArrayList<String> jsons = this.getJsonOfTestCases();
    String data = String.join(",", jsons);
    return "{\"moodle_user\": " + moodle_user + ","
            + "\"project\": \"" + project + "\","
            + "\"data\":[" + data + "]"
            + "}";
  }

  private String getJsonForCompilationError(String moodle_user, String project_id) throws VplTestException {
    String project = this.getProjectId(project_id);
    ArrayList<String> jsons = this.getJsonOfTestCases();
    String data = String.join(",", jsons);
    return "{\"moodle_user\": " + moodle_user + ","
            + "\"project\": \"" + project + "\","
            + "\"compilation_error\": true ,"
            + "\"data\":[]"
            + "}";
  }

  public String toJson(String moodle_user, String project_id) throws VplTestException {
    return this.is_compilation_error
            ? this.getJsonForCompilationError(moodle_user, project_id)
            : this.getJson(moodle_user, project_id);
  }

}
