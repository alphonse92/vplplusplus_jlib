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
   * @param classes
   * @return HashMap indexed by method id to test, VplTestMethodDescriptor as
   * value for each key
   * @throws VplTestException if exist two methods with same id
   */
  @Override
  public ArrayList<VplTest> parse(ArrayList<Class> classes) throws VplTestException {
    ArrayList<VplTest> tests = new ArrayList();
    for (Class _class_ : classes) {
      //valide if the class is a valid VPLTest Class
      if (isVplTest(_class_)) {
        //instantiate the container
        VplTest vplTestInfo = VplTest.create(_class_);
        //iterate over class methods
        for (Method method : _class_.getDeclaredMethods()) {
          //if the current method has a JUnit Test annotation 
          if (method.isAnnotationPresent(org.junit.Test.class)) {
            vplTestInfo.addTestMethodDescriptor(new VplTestMethodDescriptor(method));
          }
        }
        tests.add(vplTestInfo);
      }
    }

    return tests;
  }

  /**
   * This method valide if a class have VplPlusPlus annotation and valide if it
   * is enable
   *
   * @param _class_
   * @return
   */
  public boolean isVplTest(Class _class_) {
    return _class_.isAnnotationPresent(VplPlusPlusAnnotation.class)
            && ((VplPlusPlusAnnotation) _class_.getAnnotation(VplPlusPlusAnnotation.class)).enabled();
  }

}
