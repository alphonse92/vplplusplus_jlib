/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.runners;

import VPLPluPlusCore.interfaces.IVplRunner;
import VPLPluPlusCore.models.VplReport;
import VPLPluPlusCore.models.VplReportSuite;
import VPLPluPlusCore.models.VplTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * This class is a IVplRunner implementation. This is responsible to run the
 * tests cases of each test. A test case is a method of a test class VplTest has
 * methods, those methods are test cases.
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplJRunner implements IVplRunner {

  protected long start_time = 0;
  protected long end_time = 0;
  protected int grade = 0;

  public VplJRunner() {
  }

  public void run(Class... classes) {
    // run before
    before();
    // run after
    after();
  }

  private ArrayList orderByPriority() {
    return null;
  }

  private void before() {
    this.start_time = System.nanoTime();
  }

  private void after() {
    //get final execution time
    this.end_time = System.nanoTime();
    //print results
    this.comment(false, "Test Was Finish: " + (this.end_time - this.start_time) / 1e6);
    this.comment(false, "Execution Time: " + (this.end_time - this.start_time) / 1e6 + " Miliseconds");
    //gradding
    this.grade(String.valueOf(this.grade));
    //flush
    this.grade = 0;
  }

  protected void gradeOrFailure(String testName, int grade, AssertionError e) {
    String comment = e == null ? (testName + " Total: " + grade) : e.getMessage();
    comment(e == null, comment);
    this.addGrade(grade);
  }

  protected void comment(boolean isFailure, String comentario) {
    String out = "Comment :=>> ";
    out += (isFailure ? "[SUCCESS] " : "[FAIL] ") + comentario;
    System.out.println(out);
  }

  private void addGrade(int grade) {
    this.grade += grade;
  }

  private void grade(String grade) {
    System.out.println("Grade :=>> " + this.grade);
  }

  /**
   * This method runs all the VplTests using JUnit. JUnit will returns a report
   *
   * @param vplTests
   * @return
   */
  @Override
  public VplReportSuite run(ArrayList<VplTest> vplTests) {

    /**
     *
     * Result = JUnitCore.runClasses(_class_) ->
     * https://junit.org/junit4/javadoc/4.12/org/junit/runner/Result.html
     * Failure = Result.getFailures():Failure ->
     * https://junit.org/junit4/javadoc/4.12/org/junit/runner/notification/Failure.html
     * Description = Failure.getMethodName() ->
     * https://junit.org/junit4/javadoc/4.12/org/junit/runner/Description.html
     */
    // create a VPLReportSuite instance
    VplReportSuite suite = new VplReportSuite();

    System.out.println(vplTests.size() + " VplTests was found");

    // Take each test fo VplTests
    for (VplTest singleVplTestFromArray : vplTests) {

      System.out.println(
              "Running "
              + singleVplTestFromArray.getName()
              + "test"
      );

      // Use JUnit to run test the class normally
      Result JUnitTestResult = JUnitCore
              .runClasses(singleVplTestFromArray.getVplTestClass());

      // Create the report for the vplTest
      VplReport singleTestReport = new VplReport(singleVplTestFromArray);

      // Get failures, those failures are returned by JUnit
      List<Failure> fails = JUnitTestResult.getFailures();

      if (fails.size() > 0) {

        System.out.println(" |..." + fails.size() + " Tests was not passed");

        for (Failure failure : fails) {
          //automatically VplTest downgrade the grade
          Description jUnitTestDescription = failure.getDescription();
          String method = jUnitTestDescription.getMethodName();
          System.out.println("   |... The method " + method + " failed");
          singleTestReport.addFailure(method);
        }

      }

      //add report to suite
      suite.addReport(singleTestReport);
    }

    return suite;
  }

}
