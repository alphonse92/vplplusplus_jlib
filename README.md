# VPL ++ JLib

Java library to writte VPL plus plus tests and run VPL Test classes

VplJlib provides a set of interfaces and classes that allows to you execute Vpl tests. In few words, VplJlib is a wrapper of JUnit Runner.

This software takes all the classes of a folder, and runs all vpl tests. After it, sends the output to the VPL api. Vpl Jlib only takes the clases with the `@VplPlusPlus` annotation and the methods with `@VplTestCase` and `@Test` annotation, even if  the `@Test`annotation is present in the method.

A Test of vpl ++ looks like:

```java

import org.junit.Test;
import VPLPluPlusCore.Configurator;
import static org.junit.Assert.assertEquals;
import VPLPluPlusCore.annotations.VplPlusPlus;
import VPLPluPlusCore.annotations.VplTest;
import VPLPluPlusCore.annotations.VplTestCase;
import org.junit.Before;

@VplPlusPlus
@VplTest(   project = "5dbde95c22b5259ca46f359d")
public class CalculadoraTest{

  private Calculadora test;

  @Before
  public void setUp(){
    test = new Calculadora();
  }

  //  This method is a vpl test case b/c it has the VplTestCase annotation
  @VplTestCase(
    // the id links this method to a test case in database
    id = "5dbde95d22b5259ca46f35ab"
  )
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void testInstancia(){
    new Calculadora();
  }

  @VplTestCase(id = "5dbde95d22b5259ca46f35ab")
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void testSumar(){
    assertEquals(3, test.sumar(1, 2));
  }

  @VplTestCase(id = "5dbde95d22b5259ca46f35ab")
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void testRestar(){
    assertEquals(-1, test.restar(1, 2));
  }

  @VplTestCase(id = "5dbde95d22b5259ca46f35ab")
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void testMultiplicar(){
    assertEquals(3, test.multiplicar(1, 2));
  }

  @VplTestCase(id = "5dbde95d22b5259ca46f35ab")
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void testDividir(){
    double x = test.dividir(2, 2);
    assertEquals(1,x ,0);
  }

  // the methods below arent a vpl ++ test cases
  // b/c those methods does not have the @VplTestCase annotattion
  // and will be omited by the vpl + runner
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void SingleJUnitMethod(){
    assertEquals(2, test.multiplicar(1, 2));
  }
  
  @Test(timeout = Configurator.TIMEOUT_VERY_LONG)
  public void SingleJUnitMethodThatFails(){
    assertEquals(3, test.multiplicar(1, 2));
  }

}

```

# Install 

```sh
./install.sh
```

## Install in your actual VPL Jail Execution Server

If you want to use VPL ++ JLib Runner in your current jail execution server you need to install the JLib at first

```sh
./install_in_jail.sh
```

Then, you need to modify your vpl execution files of your activity and set the software properly.

1. In your vpl_run add:
```sh
echo  'export VPL_PLUS_PLUS_PROJECT_ID="YOUR-PROJECT-ID-GOES-HERE"' >> vpl_environment.sh
/usr/bin/vpl_run $(pwd)/
```
2. In your vpl_evaluate add:
```sh
echo  'export VPL_PLUS_PLUS_PROJECT_ID="YOUR-PROJECT-ID-GOES-HERE"' >> vpl_environment.sh
/usr/bin/vpl $(pwd)/
```

# Requeriments

To use the VPL ++  API, you need to generate a token for your JLib Runner. If you dont have it, please contact to the moodle administrator, or read the VPL ++ API documentation.

# Ussage

Vpl Jlib runs by the CLI. You can configure it ussing environment variables or parameters. The paremeters has more relevance than the environment variables.


## Enviroment variables

1. ENV: environment set the development environment. By **default** is 'development`.  The development environment will output all iformation. The production environment only will show the percentage of tests that was passed.
2. MOODLE_USER_ID: moodle user id of the class that are being tested by Jlib.
3. API_URL: the url of VPL api
4. API_TOKEN:  client JWT token for Vpl Jlib, see the administrator guide for the VPL api.
5. VPL_PLUS_PLUS_PROJECT_ID: This parameter is required, it will be used if vpl cant compile the tests.

## Parameters

1. (e): required if not was seted ENV env var
2. (f): The value **default** is where the Jlib was executed. Otherwise, if you set it, it will take all the clases and tests from it value. For example `/path/of/my/test/and/clases` will take the tests and clases from `/path/of/my/test/and/clases`
3. (u) : required if not was seted API_URL env var
4. (t) : required if not was seted API_TOKEN env var
5. (m) : required if not was seted MOODLE_USER_ID env var

## Examples

### Development

```sh
export ENV="development"
export MOODLE_USER_ID=3
export API_URL=http://localhost:1337/api/v1/project/test/case/summary/
export VPL_PLUS_PLUS_PROJECT_ID=your-project-ID
export API_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZGM3M2Q4NmUxOTY2NGFkY2U2ZmI1ZTgiLCJpZCI6LTE1NzMzMzg1MDIzNTEsInVzZXJuYW1lIjoiYXBwIiwidHlwZSI6ImFwaV9jbGllbnQiLCJpYXQiOjE1NzMzMzg1MDJ9.Liim08kZkPPlT-v5yKW9-ywvWpCSmyBMns7i8vFbIIg
java -jar VPLPlusPlus-jlib.jar
```

### Production
```sh
export MOODLE_USER_ID=3
export VPL_PLUS_PLUS_PROJECT_ID=your-project-ID
java -jar VPLPlusPlus-jlib.jar -e "production" -f "./" -u "http://localhost:1337/api/v1/project/test/case/summary/" -t "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZGM3M2Q4NmUxOTY2NGFkY2U2ZmI1ZTgiLCJpZCI6LTE1NzMzMzg1MDIzNTEsInVzZXJuYW1lIjoiYXBwIiwidHlwZSI6ImFwaV9jbGllbnQiLCJpYXQiOjE1NzMzMzg1MDJ9.Liim08kZkPPlT-v5yKW9-ywvWpCSmyBMns7i8vFbIIg"
```
