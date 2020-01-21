/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.exporters;

import VPLPluPlusCore.interfaces.IExporter;
import VPLPluPlusCore.models.VplReportSuite;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.Locale;

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

  private void printGrade(double grade) {

    DecimalFormat df2 = new DecimalFormat("#.##");
    df2.setRoundingMode(RoundingMode.DOWN);
    System.out.println("Grade :=>> " + df2.format(grade));
    
//    System.out.print("Grade :=>> ");
//    System.out.printf("%.2f", grade);
//    System.out.println("");
  }

  @Override
  public IExporter setArgs(String[] args) {
    this.args = args;
    return this;
  }

}
