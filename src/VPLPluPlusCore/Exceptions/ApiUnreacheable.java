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
    super("API is unreacheable " + url);
  }

  public ApiUnreacheable(String url, HttpRequest request, String body, HttpResponse response) {
    super("API is unreacheable " + url);
    VplLogger.getInstance().error("Error in request: " + request.getRequestLine());
    VplLogger.getInstance().error("Body: " + body);
  }

}
