/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.Exceptions;

import VPLPluPlusCore.logger.VplLogger;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

/**
 *
 * @author elieceralejandromolinavergel
 */
public class ApiUnreacheable extends VplException {

  public ApiUnreacheable(String url) {
    super("API is unreacheable ");
    VplLogger.getInstance().errorDev(url);
  }

  public ApiUnreacheable(String url, HttpRequest request, String body, HttpResponse response) {
    super("API is unreacheable ");
    VplLogger.getInstance().errorDev("Request  line: " + request.getRequestLine());
    VplLogger.getInstance().errorDev("Response line: " + response.getStatusLine());
    VplLogger.getInstance().errorDev("Body: " + body);
  }

}
