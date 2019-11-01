
import org.junit.Test;
import VPLPluPlusCore.Configurator;
import static org.junit.Assert.assertEquals;
import VPLPluPlusCore.annotations.VplPlusPlus;
import VPLPluPlusCore.annotations.VplTest;
import VPLPluPlusCore.annotations.VplTestCase;
import org.junit.Before;

@VplPlusPlus
@VplTest(
        //The name attribute must be unique at classpath, otherwise the parser
        //will return an exception
        project = "project",
        name = "Test de calculadora 1",
        created_by = "Alejandro Molina",
        //can be html
        objective = "<ul>"
        + "<li>Evaluar conocimientos básicos de Java</li>"
        + "<li>Evaluar la aplicacion de operaciones aritméticas básicas</li>"
        + "</ul>"
)
public class CalculadoraTest{

  private Calculadora test;

  @Before
  public void setUp(){
    test = new Calculadora();
  }

  @VplTestCase(
          id = "test case id",
          name = "Constructor",
          objective = "El constructor es correcto",
          grade = 13,
          successMessage = "Usted sabe crear el constructor de la clase",
          successReferenceLink = "https://www.google.com.co/search?q=como+sumar+dos+numeros+en+java&oq=como+sumar+dos+numeros+en+java",
          failureMessage = "Usted no sabe crear el constructor de la clase",
          failureReferenceLink = "https://www.google.com.co/search?q=como+sumar+dos+numeros+en+java&oq=como+sumar+dos+numeros+en+java"
  )
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void testInstancia(){
    new Calculadora();
  }

  @VplTestCase(
          id = "test case id",
          name = "sumar",
          objective = "Sumar dos parámetros",
          grade = 13,
          successMessage = "Usted sabe sumar dos números en Java",
          successReferenceLink = "https://www.google.com.co/search?q=como+sumar+dos+numeros+en+java&oq=como+sumar+dos+numeros+en+java",
          failureMessage = "Usted no sabe sumar dos números en Java",
          failureReferenceLink = "https://www.google.com.co/search?q=como+sumar+dos+numeros+en+java&oq=como+sumar+dos+numeros+en+java"
  )
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void testSumar(){
    assertEquals(3, test.sumar(1, 2));
  }

  @VplTestCase(
          id = "test case id",
          name = "restar",
          objective = "Restar dos parámetros",
          grade = 13,
          successMessage = "Usted sabe restar dos números en Java",
          successReferenceLink = "https://www.google.com.co/search?q=como+restar+dos+numeros+en+java&oq=como+sumar+dos+numeros+en+java",
          failureMessage = "Usted no sabe restar dos números en Java",
          failureReferenceLink = "https://www.google.com.co/search?q=como+restar+dos+numeros+en+java&oq=como+sumar+dos+numeros+en+java"
  )
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void testRestar(){
    assertEquals(-1, test.restar(1, 2));
  }

  @VplTestCase(
          id = "test case id",
          name = "Multiplicar",
          objective = "Multiplicar dos parámetros",
          grade = 13,
          successMessage = "Usted sabe Multiplicar dos números en Java",
          successReferenceLink = "https://www.google.com.co/search?q=como+Multiplicar+dos+numeros+en+java&oq=como+sumar+dos+numeros+en+java",
          failureMessage = "Usted no sabe Multiplicar dos números en Java",
          failureReferenceLink = "https://www.google.com.co/search?q=como+Multiplicar+dos+numeros+en+java&oq=como+sumar+dos+numeros+en+java"
  )
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void testMultiplicar(){
    assertEquals(2, test.multiplicar(1, 2));
  }

  @VplTestCase(
          id = "test case id",
          name = "Dividir",
          objective = "Dividir dos parámetros",
          grade = 13,
          successMessage = "Usted sabe Dividir dos números en Java",
          successReferenceLink = "https://www.google.com.co/search?q=como+Dividir+dos+numeros+en+java&oq=como+sumar+dos+numeros+en+java",
          failureMessage = "Usted no sabe Dividir dos números en Java",
          failureReferenceLink = "https://www.google.com.co/search?q=como+Dividir+dos+numeros+en+java&oq=como+sumar+dos+numeros+en+java"
  )
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void testDividir(){
    double x = test.dividir(2, 2);
    assertEquals(1,x ,0);
  }

  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void SingleJUnitMethod(){
    assertEquals(2, test.multiplicar(1, 2));
  }
  
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void SingleJUnitMethodThatFails(){
    assertEquals(2, test.multiplicar(1, 2));
  }

}
