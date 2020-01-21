/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.exporters;

import VPLPluPlusCore.interfaces.IExporter;
import VPLPluPlusCore.models.Test;
import VPLPluPlusCore.models.TestCase;
import VPLPluPlusCore.models.VplReport;
import VPLPluPlusCore.models.VplReportSuite;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author elieceralejandromolinavergel
 */
public class Printer implements IExporter {

  private VplReportSuite suite;
  private String[] args;

  public Printer(VplReportSuite suite) {
    this.suite = suite;
  }

  @Override
  public void export() {
    double grade = this.suite.getGrade();
    this.printGrade(grade);
  }

  private void printCommentary(String text) {
    System.out.println("Comment :=>> " + text);
  }

  private void printCommentariesForCompilationError() {
    this.printCommentary("Compilation time error");
  }

  private void printTestCasesResults() {
    for (VplReport singleReport : this.suite.getReports()) {
      Test test = singleReport.getTest();
      this.printCommentary(" === " + test.getVplTestClass().getName() + " === ");
      for (TestCase testcase : test.getArrayOfTestCases()) {
        String success = "";
        String fail = "<b>[FAIL]</b> ";
        String test_case_name = testcase.getMethod().getName();
        String textCommentary = (testcase.isSuccess() ? success : fail) + test_case_name;
        String gradeLabel = (testcase.isSuccess() ? String.valueOf(testcase.grade()) : "0") + " PTS";
        String commentary = textCommentary + " " + gradeLabel;
        this.printCommentary(commentary);
      }
      this.printCommentary(" ");
    }
  }

  private void printGrade(double grade) {

    DecimalFormat df2 = new DecimalFormat("#.##");
    df2.setRoundingMode(RoundingMode.DOWN);

    if (this.suite.getCompilationError()) {
      this.printCommentariesForCompilationError();
    } else {
      this.printTestCasesResults();
    }
    System.out.println("Comment :=>> Final grade: " + grade);
    System.out.println("Grade :=>> " + df2.format(grade));
  }

  @Override
  public IExporter setArgs(String[] args) {
    this.args = args;
    return this;
  }

}
