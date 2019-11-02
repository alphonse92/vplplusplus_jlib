/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.exporters;

import VPLPluPlusCore.Exceptions.ApiExporterBadParameter;
import VPLPluPlusCore.Exceptions.ApiUnreacheable;
import VPLPluPlusCore.Exceptions.NoUrlException;
import VPLPluPlusCore.Exceptions.VplException;
import VPLPluPlusCore.interfaces.IExporter;
import VPLPluPlusCore.logger.VplLogger;
import VPLPluPlusCore.models.VplReport;
import VPLPluPlusCore.models.VplReportSuite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.UnrecognizedOptionException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
  private String moodle_user;

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

      ArrayList<VplReport> reports = this.suite.getReports();

      for (VplReport singleReport : reports) {

        HttpPost request = new HttpPost(this.url);
        String body = singleReport.toJson("1");
        request.setEntity(new StringEntity(body));

        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        if (this.token != null) {
          request.setHeader("Authorization", "Bearer " + this.token);
        }

        Future<HttpResponse> future = httpclient.execute(request, null);
        HttpResponse response = future.get();

        StatusLine status = response.getStatusLine();
        int statuscode = status.getStatusCode();

        VplLogger.getInstance().logLn("Response : " + status.toString());

        if (!this.isResponseOk(statuscode)) {
          throw new ApiUnreacheable(this.url, request, body, response);
        }

      }

    } catch (InterruptedException | ExecutionException | IOException ex) {
      throw new ApiUnreacheable(this.url);
    } finally {
      try {
        VplLogger.getInstance().logLn("Closing client");
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
    options.addOption("m", "moodle_user", true, "moodle user");
    options.addOption("f", "files", true, "default f parameter of vplloader");
    options.addOption("e", "environment", true, "default e parameter of vplloader");

    CommandLineParser parser = new DefaultParser();
    try {

      CommandLine cmd = parser.parse(options, this.args);
      this.url = cmd.getOptionValue("u");
      this.token = cmd.getOptionValue("t");
      this.moodle_user = cmd.getOptionValue('m');

      if (this.moodle_user == null) {
        throw new ApiExporterBadParameter(options, "moodle_user");
      }

      return this;

    } catch (UnrecognizedOptionException ex) {
      throw new ApiExporterBadParameter(options);
    }

  }

}
