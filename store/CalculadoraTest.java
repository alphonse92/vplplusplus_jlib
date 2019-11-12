import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import VPLPluPlusCore.Configurator;
import VPLPluPlusCore.annotations.VplPlusPlus;
import VPLPluPlusCore.annotations.VplTest;
import VPLPluPlusCore.annotations.VplTestCase;

@VplPlusPlus
@VplTest(   project = "5dcae8d03bd8e10025c77fff")
public class CalculadoraTest{

  private Calculadora test;

  @Before
  public void setUp(){
    test = new Calculadora();
  }

  //  This method is a vpl test case b/c it has the VplTestCase annotation
  @VplTestCase(
    // the id links this method with a test case in database
    id = "5dcae8d13bd8e10025c78009"
  )
  @Test()
  public void testInstancia(){
    new Calculadora();
  }

  @VplTestCase(id = "5dcae8d13bd8e10025c78009")
  @Test()
  public void testSumar(){
    assertEquals(3, test.sumar(1, 2));
  }

  @VplTestCase(id = "5dcae8d13bd8e10025c78009")
  @Test()
  public void testRestar(){
    assertEquals(-1, test.restar(1, 2));
  }

  @VplTestCase(id = "5dcae8d13bd8e10025c78009")
  @Test()
  public void testMultiplicar(){
    assertEquals(3, test.multiplicar(1, 2));
  }

  @VplTestCase(id = "5dcae8d13bd8e10025c78009")
  @Test()
  public void testDividir(){
    double x = test.dividir(2, 2);
    assertEquals(1,x ,0);
  }

  // the methods below arent a vpl ++ test cases
  // b/c those methods does not have the @VplTestCase annotattion
  // and will be omited by the vpl + runner
  @Test()
  public void SingleJUnitMethod(){
    assertEquals(2, test.multiplicar(1, 2));
  }
  
  @Test()
  public void SingleJUnitMethodThatFails(){
    assertEquals(3, test.multiplicar(1, 2));
  }

}