/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import VPLPluPlusCore.Exceptions.VplTestException;
import VPLPluPlusCore.logger.VplLogger;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplReport {

  private Test vplTest;

  public VplReport(Test vplTest) {
    this.vplTest = vplTest;
  }
  
  public Test getTest(){
    return this.vplTest;
  }

  public static VplReport getReportForCompilationError() {
    Test test = new Test();
    test.setNoCompiled(true);
    return new VplReport(test);
  }

  private double getGradeByAvg() {
    double totalOfTests = this.vplTest.getTestCasesQuantity();
    double approved = this.vplTest.getTestCasesApprovedQuantity();

    if (approved == 0) {
      return 0;
    }
    if (approved == totalOfTests) {
      return 100;
    }

    double percentage = (approved / totalOfTests) * 100;

    return percentage;
  }

  private double getGradeTestCase() {

    double gradeLost = this.vplTest.getGradeLost();
    double maxGrade = this.vplTest.getMaxGrade();
    double percentage = gradeLost == 0
            ? 100
            : gradeLost == maxGrade
                    ? 0
                    : 100 - ((gradeLost / maxGrade) * 100);
    return percentage;
  }

  public double getGrade() {
    VplLogger logger = VplLogger.getInstance();
    double grade = this.vplTest.shouldEvaluateByAvg()
            ? this.getGradeByAvg()
            : this.getGradeTestCase();
    logger.logLn("Gradding" + this.vplTest.name() + ": " + String.valueOf(grade));
    return grade;
  }
  
  public String toJson(String moodle_user) throws VplTestException {
    return this.vplTest.toJson(moodle_user, null);
  }

  public String toJson(String moodle_user, String project_id) throws VplTestException {
    return this.vplTest.toJson(moodle_user, project_id);
  }

}
