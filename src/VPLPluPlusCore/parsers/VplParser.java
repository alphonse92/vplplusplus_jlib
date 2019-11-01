package VPLPluPlusCore.parsers;

import VPLPluPlusCore.models.TestCase;
import VPLPluPlusCore.models.Test;
import VPLPluPlusCore.Exceptions.VplTestException;
import VPLPluPlusCore.annotations.VplTestCase;
import VPLPluPlusCore.interfaces.IVplParser;
import java.lang.reflect.Method;
import java.util.ArrayList;
import VPLPluPlusCore.annotations.VplPlusPlus;

/**
 * 
 * This is the Default class parser. This class converts the classes
 * that have the annotation of vpltest to VPLTest classes
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 */
public class VplParser implements IVplParser {

  public VplParser() {
  }

  /**
   * This method parse class to VplTest Class and return it instance
   *
   * @param classesFromExecutionFiles is an array of Classes, those classes are
   * loaded from urls in each execution file
   * @return Array of VplTest class
   * @throws VplTestException if exist two methods with same id
   */
  @Override
  public ArrayList<Test> parse(ArrayList<Class> classesFromExecutionFiles) throws VplTestException {

    ArrayList<Test> ArrayOfVplTests = new ArrayList();

    for (Class classLoadedFromExecutionFile : classesFromExecutionFiles) {
      // if the classLoadedFromExecution file is a vpl test
      if (isTheClassAVplTest(classLoadedFromExecutionFile)) {
        // Then create a VPLtest for the loaded class
        Test test = Test.create(classLoadedFromExecutionFile);
        //Get the methods and iterate on it
        for (Method method : classLoadedFromExecutionFile.getDeclaredMethods()) {
          // if the method has a @test tag annotation
          // add to the test methods descriptors
          if (this.shoudlAddTestCaseToTest(method)) {
            test.addTestCase(new TestCase(method));
          }
        }
        // Add to the array of VPL tests
        ArrayOfVplTests.add(test);
      }
    }

    return ArrayOfVplTests;
  }
  
  private boolean shoudlAddTestCaseToTest(Method method){
    return method.isAnnotationPresent(org.junit.Test.class)
             && method.isAnnotationPresent(VplTestCase.class);
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
            .isAnnotationPresent(VplPlusPlus.class)
            && ((VplPlusPlus) classLoadedFromExecutionFile
                    .getAnnotation(VplPlusPlus.class))
                    .enabled();
  }

}
