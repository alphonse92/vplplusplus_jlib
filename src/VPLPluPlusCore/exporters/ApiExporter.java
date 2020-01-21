/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPLPluPlusCore.exporters;

import VPLPluPlusCore.Exceptions.ApiError;
import VPLPluPlusCore.Exceptions.ApiExporterBadParameter;
import VPLPluPlusCore.Exceptions.ApiUnreacheable;
import VPLPluPlusCore.Exceptions.NoUrlException;
import VPLPluPlusCore.Exceptions.VplException;
import VPLPluPlusCore.Exceptions.VplTestException;
import VPLPluPlusCore.interfaces.IExporter;
import VPLPluPlusCore.logger.VplLogger;
import VPLPluPlusCore.models.VplReport;
import VPLPluPlusCore.models.VplReportSuite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
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
  private String project_id;

  public ApiExporter(VplReportSuite suite) {
    this.suite = suite;
  }

  @Override
  public void export() throws VplException, NoUrlException, ApiUnreacheable {

    if (this.moodle_user == null) {
      return;
    }

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
        String body = singleReport.toJson(this.moodle_user, this.project_id);
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
          throw new ApiError(this.url, this.token, request, body, response);
        }

      }

    } catch (InterruptedException | ExecutionException | IOException ex) {
      throw new ApiUnreacheable(this.url);
    } catch (VplTestException ex) {
      Logger.getLogger(ApiExporter.class.getName()).log(Level.SEVERE, null, ex);
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
  public IExporter setArgs(String[] args) throws ParseException, ApiExporterBadParameter {

    this.args = args;

    Options options = new Options();

    options.addOption("t", "token", true, "Api token");
    options.addOption("u", "url", true, "Api url");
    options.addOption("m", "moodle_user", true, "moodle user");
    options.addOption("f", "files", true, "default f parameter of vplloader");
    options.addOption("e", "environment", true, "default e parameter of vplloader");

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, this.args);

    Map<String, String> env = System.getenv();

    String PROJECT_ID = env.get("VPL_PLUS_PLUS_PROJECT_ID");
    this.project_id = PROJECT_ID;

    String userOptionValue = cmd.getOptionValue("m");
    String userFromEnv = env.get("MOODLE_USER_ID");
    this.moodle_user = userOptionValue == null ? userFromEnv : userOptionValue;

    String urlFromEnv = env.get("API_URL");
    String urlFromOpts = cmd.getOptionValue("u");
    this.url = urlFromOpts == null ? urlFromEnv : urlFromOpts;

    String tokenFromEnv = env.get("API_TOKEN");
    String tokenFromOpts = cmd.getOptionValue("t");
    this.token = tokenFromOpts == null ? tokenFromEnv : tokenFromOpts;

    VplLogger.getInstance().logLn("User id: " + String.valueOf(this.moodle_user));

    if (this.moodle_user == null) {
      VplLogger.getInstance().logLn("User id¡ is null. Its looks is a teacher testing the activity :)");
      return this;
    }

    if (this.url == null) {
      throw new ApiExporterBadParameter(options, "API_URL (-u)", "Api url was no seted in environment variables or passed down by arguments");
    }
    if (this.token == null) {
      throw new ApiExporterBadParameter(options, "API_TOKEN (-t)", "Api token was no seted in environment variables or passed down by arguments");
    }

    return this;

  }

}
