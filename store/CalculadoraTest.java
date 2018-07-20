
import org.junit.Test;
import VPLPluPlusCore.Configurator;
import static org.junit.Assert.assertEquals;
import VPLPluPlusCore.annotations.VplPlusPlusAnnotation;
import VPLPluPlusCore.annotations.VplTestInfoAnnotation;
import VPLPluPlusCore.annotations.VplTestDescriptorAnnotation;
import org.junit.Before;

@VplPlusPlusAnnotation
@VplTestInfoAnnotation(
        //The name attribute must be unique at classpath, otherwise the parser
        //will return an exception
        name = "Test de calculadora 1",
        tags = "Calculadora, operaciones basicas",
        created_by = "Alejandro Molina",
        maxGrade = 25,
        //can be html
        objetive = "<ul>"
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

  @VplTestDescriptorAnnotation(
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

  @VplTestDescriptorAnnotation(
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

  @VplTestDescriptorAnnotation(
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

  @VplTestDescriptorAnnotation(
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

  @VplTestDescriptorAnnotation(
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
    assertEquals(0.5, test.dividir(1, 2));
  }

  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void SingleJUnitMethod(){
    assertEquals(2, test.multiplicar(1, 2));
  }
  
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void SingleJUnitMethodThatFails(){
    assertEquals(200, test.multiplicar(1, 2));
  }

}
