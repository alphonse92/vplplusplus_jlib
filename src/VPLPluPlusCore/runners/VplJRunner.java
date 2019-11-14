/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.runners;

import VPLPluPlusCore.Exceptions.NoTestsFound;
import VPLPluPlusCore.interfaces.IVplRunner;
import VPLPluPlusCore.logger.VplLogger;
import VPLPluPlusCore.models.VplReport;
import VPLPluPlusCore.models.VplReportSuite;
import VPLPluPlusCore.models.Test;
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

  /**
   * This method runs all the VplTests using JUnit. JUnit will returns a report
   *
   * @param vplTests
   * @return
   */
  @Override
  public VplReportSuite run(ArrayList<Test> vplTests) throws NoTestsFound {
    this.before();
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

    VplLogger.getInstance().logLn(vplTests.size() + " VplTests was found");

    if (vplTests.size() == 0) {
      throw new NoTestsFound();
    }

    // Take each test fo VplTests
    for (Test vplTest : vplTests) {

      VplLogger.getInstance().logLn(
              "   Running "
              + vplTest.name()
              + " test"
      );

      // Use JUnit to run test the class normally
      Result JUnitTestResult = JUnitCore
              .runClasses(vplTest.getVplTestClass());

      // Create the report for the vplTest
      VplReport singleTestReport = new VplReport(vplTest);
      // Get failures, those failures are returned by JUnit
      List<Failure> fails = JUnitTestResult.getFailures();

      if (fails.size() > 0) {

        VplLogger.getInstance().logLn("    |..." + fails.size() + " Tests was not passed");

        for (Failure failure : fails) {

          //automatically VplTest downgrade the grade
          Description jUnitTestDescription = failure.getDescription();
          String method = jUnitTestDescription.getMethodName();
          VplLogger.getInstance().logLn("       |... The method " + method + " failed");
          // setting the failure
          vplTest.setTestCaseFailure(method);

        }

      }

      //add report to suite
      suite.addReport(singleTestReport);

    }

    this.after(suite);

    return suite;
  }

  private void before() {
    this.start_time = System.nanoTime();
  }

  private void after(VplReportSuite suite) {
    this.end_time = System.nanoTime();
    this.printTime("Executed in ", (this.end_time - this.start_time) / 1e6);
  }

  private void printTime(String title, double time) {
    String timeStr = String.format("%.2f", time);
    this.printCommentary(title + timeStr + " Miliseconds");
  }

  private void printCommentary(String commentary) {
    boolean isFailure = true;
    String prefix = isFailure ? "[SUCCESS] " : "[FAIL] ";
    String out = "Comment: = >>"; // + prefix
    out += commentary;
    VplLogger.getInstance().logLn(out);
  }

}
