/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.Exceptions;

import VPLPluPlusCore.logger.VplLogger;
import java.io.IOException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
/**
 *
 * @author elieceralejandromolinavergel
 */
public class ApiError extends VplException {

  public ApiError(String url) {
    super("API throw an error ");
    VplLogger.getInstance().errorDev(url);
  }

  public ApiError(String url, String token, HttpRequest request, String body, HttpResponse response) throws IOException {
    super("API throw an error " +EntityUtils.toString(response.getEntity(), "UTF-8"));
    VplLogger.getInstance().errorDev("Authorization token: " + token);
    VplLogger.getInstance().errorDev("Request  line: " + request.getRequestLine());
    VplLogger.getInstance().errorDev("Response line: " + response.getStatusLine());
    VplLogger.getInstance().errorDev("Body: " + body);
  }

}
