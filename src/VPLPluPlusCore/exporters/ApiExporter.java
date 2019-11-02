/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.exporters;

import VPLPluPlusCore.Exceptions.ApiUnreacheable;
import VPLPluPlusCore.Exceptions.NoUrlException;
import VPLPluPlusCore.Exceptions.VplException;
import VPLPluPlusCore.interfaces.IExporter;
import VPLPluPlusCore.logger.VplLogger;
import VPLPluPlusCore.models.VplReportSuite;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

/**
 *
 * @author elieceralejandromolinavergel
 */
public class ApiExporter implements IExporter {

  private VplReportSuite suite;
  private String[] args;
  private String url;
  private String token;

  public ApiExporter(VplReportSuite suite) {
    this.suite = suite;
  }

  @Override
  public void export() throws VplException, NoUrlException, ApiUnreacheable {

    if (this.url == null) {
      throw new NoUrlException();
    }

    CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
    
    try {
      httpclient.start();

      VplLogger.getInstance().logLn("requesting to: " + this.url);

      HttpPost request = new HttpPost(this.url);
      Future<HttpResponse> future = httpclient.execute(request, null);
      HttpResponse response = future.get();

      StatusLine status = response.getStatusLine();
      int statuscode = status.getStatusCode();

      if (!this.isResponseOk(statuscode)) {
        throw new ApiUnreacheable(this.url);
      }

      httpclient.close();

    } catch (InterruptedException | ExecutionException | IOException ex) {
      throw new ApiUnreacheable(this.url);
    } finally {
      try {
        VplLogger.getInstance().logLn("Done");
        httpclient.close();
      } catch (IOException ex) {
        Logger.getLogger(ApiExporter.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private boolean isResponseOk(int statuscode) {
    return statuscode >= 200 && statuscode <= 299;
  }

  @Override
  public IExporter setArgs(String[] args) throws Exception {

    this.args = args;

    Options options = new Options();

    options.addOption("t", "token", true, "Api token");
    options.addOption("u", "url", true, "Api url");
    options.addOption("f", "files", true, "default f parameter of vplloader");
    options.addOption("e", "environment", true, "default e parameter of vplloader");

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, this.args);

    this.url = cmd.getOptionValue("u");
    this.token = cmd.getOptionValue("t");

    return this;
  }

}
