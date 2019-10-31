/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplReportSuite{

  private final ArrayList<VplReport> singleReports;
  public static final boolean ORDER_ASC = true;
  public static final boolean ORDER_DESC = false;
  public static final int ORDER_BY_GRADE = 1;

  public VplReportSuite(){
    this.singleReports = new ArrayList();
  }
  public VplReportSuite addReport(VplReport singleTestReport){
    this.singleReports.add(singleTestReport);
    return this;
  }

  /**
   *
   * Order the suite of single VplReport by parameter, comparator can be
   * VplReportSuite.ORDER_BY_GRADE
   *
   * @param comparator value thats represent a comparator from VplReportsuite
   * @param isAsc boolean thats indicate if order asc or desc
   * @return VplReportSuite instance
   */
  public VplReportSuite orderBy(byte comparator, boolean isAsc){
    Collections.sort(this.singleReports, getComparator(comparator, isAsc));
    return this;
  }

  /**
   * Order by custom comparator, the comparator will receives two instances of
   * VplReport
   *
   * @param comparator
   * @return VplReportSuite instance
   */
  public VplReportSuite orderBy(Comparator comparator){
    Collections.sort(this.singleReports, comparator);

    return this;
  }

  private Comparator getComparator(int id, boolean reverse){
    Comparator<VplReport> out = new Comparator<VplReport>(){
      @Override
      public int compare(VplReport current, VplReport next){
        return current.getGrade() > next.getGrade() ? -1 : (current.getGrade() < next.getGrade()) ? 1 : 0;
      }
    };
    //if exists more than one comparators
    if(id == VplReportSuite.ORDER_BY_GRADE){

    }
    return reverse ? out.reversed() : out;
  }

}
