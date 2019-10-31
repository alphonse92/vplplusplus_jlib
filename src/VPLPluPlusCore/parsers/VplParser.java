package VPLPluPlusCore.parsers;

import VPLPluPlusCore.models.VplTestMethodDescriptor;
import VPLPluPlusCore.models.VplTest;
import VPLPluPlusCore.Exceptions.VplTestException;
import VPLPluPlusCore.annotations.VplPlusPlusAnnotation;
import VPLPluPlusCore.interfaces.IVplParser;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplParser implements IVplParser {

  public VplParser() {
  }

  /**
   * This method parse class to VplTest Class and return it instance
   *
   * @param classesFromExecutionFiles is an array of Classes, those clases are
   * loaded from urls in each execution file
   * @return HashMap indexed by method id to test, VplTestMethodDescriptor as
   * value for each key
   * @throws VplTestException if exist two methods with same id
   */
  @Override
  public ArrayList<VplTest> parse(ArrayList<Class> classesFromExecutionFiles) throws VplTestException {

    ArrayList<VplTest> ArrayOfVplTests = new ArrayList();

    for (Class classLoadedFromExecutionFile : classesFromExecutionFiles) {
      // if the classLoadedFromExecution file is a vpl test
      if (isTheClassAVplTest(classLoadedFromExecutionFile)) {
        // Then create a VPLtest for the loaded class
        VplTest vplTestInfo = VplTest.create(classLoadedFromExecutionFile);
        //Get the methods and iterate on it
        for (Method method : classLoadedFromExecutionFile.getDeclaredMethods()) {
          // if the method has a @test tag annotation
          // add to the test methods descriptors
          if (method.isAnnotationPresent(org.junit.Test.class)) {
            vplTestInfo.addTestMethodDescriptor(new VplTestMethodDescriptor(method));
          }
        }
        // Add to the array of VPL tests
        ArrayOfVplTests.add(vplTestInfo);
      }
    }

    return ArrayOfVplTests;
  }

  /**
   * Validate if the class has the VplPlusPlusAnnotation annotation and if it is
   * enable
   *
   * @param classLoadedFromExecutionFile
   * @return true if the class is a VPL test
   */
  private boolean isTheClassAVplTest(Class classLoadedFromExecutionFile) {
    return classLoadedFromExecutionFile
            .isAnnotationPresent(VplPlusPlusAnnotation.class)
            && ((VplPlusPlusAnnotation) classLoadedFromExecutionFile
                    .getAnnotation(VplPlusPlusAnnotation.class))
                    .enabled();
  }

}
