/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import VPLPluPlusCore.logger.VplLogger;
import java.util.ArrayList;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplReportSuite {

  private final ArrayList<VplReport> singleReports;
  public static final boolean ORDER_ASC = true;
  public static final boolean ORDER_DESC = false;
  public static final int ORDER_BY_GRADE = 1;

  public VplReportSuite() {
    this.singleReports = new ArrayList();
  }

  public VplReportSuite addReport(VplReport singleTestReport) {
    this.singleReports.add(singleTestReport);
    return this;
  }

  public ArrayList<VplReport> getReports() {
    return this.singleReports;
  }

  public double getGrade() {

    double totalOfReports = this.singleReports.size();
    double totalGrade = 0;

    for (VplReport testReport : this.singleReports) {
      totalGrade += testReport.getGrade();
    }
    
    double grade = totalGrade / totalOfReports;
    VplLogger logger = VplLogger.getInstance();
    logger.logLn("Final grade: "+grade);
    return grade;
    
  }
  

}
