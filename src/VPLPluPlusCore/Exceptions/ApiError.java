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
public class ApiError extends VplException {

  public ApiError(String url) {
    super("API throw an error ");
    VplLogger.getInstance().error(url);
  }

  public ApiError(String url, String token, HttpRequest request, String body, HttpResponse response) {
    super("API throw an error ");
    VplLogger.getInstance().error("Authorization token: " + token);
    VplLogger.getInstance().error("Request  line: " + request.getRequestLine());
    VplLogger.getInstance().error("Response line: " + response.getStatusLine());
    VplLogger.getInstance().error("Body: " + body);
  }

}
