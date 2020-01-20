/**
* VplJUnit version 1.0

Esta clase fue generada automaticamente y incorpora
las funcionalidades de VPL ++.

Puedes modificar esta clase manualmente a tu gusto. 
Si deseas puedes revisar la documentacion de VPL-JLib
y conocer como mejorar las pruebas unitarias de JUnit
con Vpl Jlib

Debajo puedes anadir tus imports.
*/
import VPLPluPlusCore.annotations.VplPlusPlus;
import VPLPluPlusCore.annotations.VplTest;
import VPLPluPlusCore.annotations.VplTestCase;
import static org.junit.Assert.assertEquals;
import org.junit.*;
@VplPlusPlus
@VplTest(project = "5e2506e909226200286a001d")
public class CasaDeCambioTest{
  
  /**
 * Sets up the test fixture.
 *
 * Called before every test case method.
 */
@Before
public void  setUp(){
}

/**
 * Tears down the test fixture.
 *
 * Called after every test case method.
 */
@After
public void  tearDown(){
}


  
  @VplTestCase(id = "5e2506e909226200286a0023" , grade = 4 )
  @Test()
  public void testCambiarPrecioDelBolivar4(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        boolean result = instance.cambiarPrecioDelBolivar(-2, -1);
        assertEquals(false, result);

  }


  @VplTestCase(id = "5e2506e909226200286a0021" , grade = 4 )
  @Test()
  public void testCambiarPrecioDelBolivar2(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        boolean result = instance.cambiarPrecioDelBolivar(-1, 1);
        assertEquals(false, result);

  }


  @VplTestCase(id = "5e2506e909226200286a0026" , grade = 4 )
  @Test()
  public void testCambiarPrecioDelBolivar7(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        boolean result = instance.cambiarPrecioDelBolivar(1, 2);
        assertEquals(true, result);

  }


  @VplTestCase(id = "5e2506e909226200286a002b" , grade = 4 )
  @Test()
  public void testComprarBolivares4(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarPesos(3);
        instance.comprarBolivares(1);
        instance.comprarBolivares(1);
        assertEquals(2, instance.getBolivaresComprados());

  }


  @VplTestCase(id = "5e2506e909226200286a0030" , grade = 4 )
  @Test()
  public void testVenderBolivares4(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarBolivares(3);
        instance.venderBolivares(1);
        instance.venderBolivares(1);
        assertEquals(2, instance.getBolivaresVendidos());

  }


  @VplTestCase(id = "5e2506e909226200286a001f" , grade = 4 )
  @Test()
  public void testGetGananciaEnUnBolivar(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1.0f, 2.0f);
        float result = instance.getGananciaEnUnBolivar();
        assertEquals(1.0, result, 0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a0029" , grade = 4 )
  @Test()
  public void testComprarBolivares2(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarPesos(2);
        instance.comprarBolivares(1);
        assertEquals(1.0, instance.getPesosEnCaja(), 0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a0024" , grade = 4 )
  @Test()
  public void testCambiarPrecioDelBolivar5(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        boolean result = instance.cambiarPrecioDelBolivar(1, 1);
        assertEquals(false, result);

  }


  @VplTestCase(id = "5e2506e909226200286a002e" , grade = 4 )
  @Test()
  public void testVenderBolivares2(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarPesos(1);
        instance.inyectarBolivares(2);
        instance.venderBolivares(1);
        assertEquals(3.0, instance.getPesosEnCaja(), 0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a0033" , grade = 4 )
  @Test()
  public void testGetGanancias(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarBolivares(50);
        instance.venderBolivares(10);
        assertEquals(16.8, instance.getGanancias(), 0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a0025" , grade = 4 )
  @Test()
  public void testCambiarPrecioDelBolivar6(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        boolean result = instance.cambiarPrecioDelBolivar(2, 1);
        assertEquals(false, result);

  }


  @VplTestCase(id = "5e2506e909226200286a002f" , grade = 4 )
  @Test()
  public void testVenderBolivares3(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarBolivares(2);
        instance.venderBolivares(1);
        assertEquals(1.0, instance.getBolivaresEnCaja(), 0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a002a" , grade = 4 )
  @Test()
  public void testComprarBolivares3(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarPesos(2);
        instance.inyectarBolivares(1);
        instance.comprarBolivares(1);
        assertEquals(2.0, instance.getBolivaresEnCaja(), 0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a0034" , grade = 4 )
  @Test()
  public void testInyectarPesos1(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.inyectarPesos(1);
        instance.inyectarPesos(1);
        assertEquals(2.0, instance.getPesosEnCaja(),0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a0035" , grade = 4 )
  @Test()
  public void testInyectarPesos2(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.inyectarPesos(1);
        instance.inyectarPesos(-1);
        assertEquals(1.0, instance.getPesosEnCaja(),0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a0027" , grade = 4 )
  @Test()
  public void testCambiarPrecioDelBolivar8(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        assertEquals(1.0, instance.getPrecioDeCompra(), 0.01);
        assertEquals(2.0, instance.getPrecioDeVenta(), 0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a002c" , grade = 4 )
  @Test()
  public void testComprarBolivares5(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarPesos(5);
        boolean result = instance.comprarBolivares(6);
        assertEquals(false, result);

  }


  @VplTestCase(id = "5e2506e909226200286a0022" , grade = 4 )
  @Test()
  public void testCambiarPrecioDelBolivar3(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        boolean result = instance.cambiarPrecioDelBolivar(-1, 0);
        assertEquals(false, result);

  }


  @VplTestCase(id = "5e2506e909226200286a0036" , grade = 4 )
  @Test()
  public void testInyectarBolivares1(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.inyectarBolivares(1);
        instance.inyectarBolivares(1);
        assertEquals(2.0, instance.getBolivaresEnCaja(),0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a0031" , grade = 4 )
  @Test()
  public void testVenderBolivares5(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarBolivares(5);
        boolean result = instance.venderBolivares(6);
        assertEquals(false, result);

  }


  @VplTestCase(id = "5e2506e909226200286a0020" , grade = 4 )
  @Test()
  public void testCambiarPrecioDelBolivar1(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        boolean result = instance.cambiarPrecioDelBolivar(0, 1);
        assertEquals(false, result);

  }


  @VplTestCase(id = "5e2506e909226200286a002d" , grade = 4 )
  @Test()
  public void testVenderBolivares1(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarBolivares(2);
        boolean result = instance.venderBolivares(1);
        assertEquals(true, result);

  }


  @VplTestCase(id = "5e2506e909226200286a0028" , grade = 4 )
  @Test()
  public void testComprarBolivares1(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarPesos(2);
        boolean result = instance.comprarBolivares(1);
        assertEquals(true, result);

  }


  @VplTestCase(id = "5e2506e909226200286a0037" , grade = 4 )
  @Test()
  public void testInyectarBolivares2(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.inyectarBolivares(1);
        instance.inyectarBolivares(-1);
        assertEquals(1.0, instance.getBolivaresEnCaja(), 0.01);

  }


  @VplTestCase(id = "5e2506e909226200286a0032" , grade = 4 )
  @Test()
  public void testGetImpuestos(){     
    
        CasaDeCambio instance = new CasaDeCambio();
        instance.cambiarPrecioDelBolivar(1, 2);
        instance.inyectarBolivares(50);
        instance.venderBolivares(10);
        assertEquals(3.2, instance.getImpuestos(), 0.01);

  }

}