
/**
* VplJUnit version 1.0
* This class was generated automatically, and adds 
* the VPL++ api to improve your tests with extra functionalities.
* 
* You can modify this class manually, please review the
* VplJunit runner documentation of vpl ++  to know
* how to improve your JUnit tests with vpl
* 
* If you need help please contact to the Vpl++ creator
*/

import VPLPluPlusCore.annotations.VplPlusPlus;
import VPLPluPlusCore.annotations.VplTest;
import VPLPluPlusCore.annotations.VplTestCase;

// set here your imports
// YOU NEED TO SET YOUR IMPORTS MANUALLY 

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

// end of your imports

@VplPlusPlus
@VplTest(project = "5dcdd9d9bf2717001ce0c96c")
public class CalculadoraTestWithGrade{
  
   private Calculadora test;

  @Before
  public void setUp(){
    test = new Calculadora();
  }
	

  
  @VplTestCase(id = "5dcdd9d9bf2717001ce0c96e", grade = 5)
  @Test()
  public void SumarTest(){     
     assertEquals(2, test.sumar(1, 2));
  }


  @VplTestCase(id = "5dcdd9d9bf2717001ce0c96f", grade = 5)
  @Test()
  public void MultiplicarTest(){     
    assertEquals(2, test.multiplicar(1, 2));
  }


  @VplTestCase(id = "5dcdd9d9bf2717001ce0c970", grade = 5)
  @Test()
  public void DividirTest(){     
    double x = test.dividir(2, 2);
assertEquals(1,x ,0);
  }


  @VplTestCase(id = "5dcdd9d9bf2717001ce0c971", grade = 5)
  @Test()
  public void RestarTest(){     
     assertEquals(-1, test.restar(1, 2));
  }

}
