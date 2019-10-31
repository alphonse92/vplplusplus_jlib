package VPLPluPlusCore.factories.parser;

import VPLPluPlusCore.interfaces.IVplParser;
import VPLPluPlusCore.parsers.VplParser;

/**
 * A parser is a class that parse another classes to VPLTest classes, if 
 * it applied.
 * 
 * Not all classes should be converted to VPLTest class, only the classes
 * that have the VPL test annotations.
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
