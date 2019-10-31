/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.models;

import java.util.ArrayList;

/**
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplReport {

  private final VplTest vplTest;
  private int downgradeMeasure = 0;
  private final ArrayList<VplTestMethodDescriptor> failures;

  public VplReport(VplTest vplTest) {
    this.failures = new ArrayList();
    this.vplTest = vplTest;
  }

  /**
   * Add failure to report. The method gets the methodName referenced to
   * VplTestMethodDescriptor and add it to array. After adds to downgradeMeasure
   * the VplTestMethodDescriptor's grade
   *
   * @param methodName
   * @return
   */
  public VplReport addFailure(String methodName) {

    return this
            .addToArrayFailures(this.vplTest.getMethodDescriptor(methodName))
            .punish(this.vplTest.getMethodDescriptor(methodName).getGrade());
  }

  private VplReport addToArrayFailures(VplTestMethodDescriptor method) {
    this.failures.add(method);
    return this;
  }

  public VplReport punish(int grade) {
    this.downgradeMeasure += grade;
    return this;
  }

  /**
   * Substract test's max grade with the grade to punish when exist some
   * failures and return the result
   *
   * @return result of this.vplTest.getMaxGrade() - this.gradePunisher
   */
  public int getGrade() {
    return this.vplTest.getMaxGrade() - this.downgradeMeasure;
  }

}
