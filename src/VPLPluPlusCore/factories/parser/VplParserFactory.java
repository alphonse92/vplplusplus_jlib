package VPLPluPlusCore.factories.parser;

import VPLPluPlusCore.interfaces.IVplParser;
import VPLPluPlusCore.parsers.VplParser;

/**
 * A parser is a Class that covert a JUnit test to VPL++ JUnit test, using the
 * VPL++ anotations above each methods in the test.
 *
 * the responsability of a Parser is desacoplate the teacher test, avoid to him
 * to learn VPL++ concepts and rare implementations.
 *
 * @author Eliecer Alejandro Molina Vergel <alejandro_mover@hotmail.com>
 *
 */
public class VplParserFactory {

  public static final byte DEFAULT = 0;

  public static IVplParser getParser() {
    return new VplParser();
  }

  public static IVplParser getParser(byte factory) {
    if (factory == DEFAULT) {
      return new VplParser();
    }
    return new VplParser();
  }
}
