package com.nfc.util;

import com.nfc.main.controllers.Constants;


import java.io.InputStream;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author NFC
 */
public class MailRestService {

    public String sendMail(List<String> recipients, List<String> recipientsCC, String subject, String body) {
        JSONObject json = new JSONObject();
        String result = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            if (Constants.ENV.equals(Constants.TESTING)) {
                if (recipientsCC != null) {
                    recipientsCC.clear();
                }

                recipients.clear();
                recipients.add(Constants.TESTMAILID);
            }
            json.put("recipients", recipients);
            json.put("recipientsCC", recipientsCC);
            json.put("subject", subject);
            json.put("body", body);
            json.put("senderMaidId", "noreply@nfc.gov.in");
            json.put("senderPassWord", "no@reply");
            StringEntity params = new StringEntity(json.toString());

            HttpPost request = new HttpPost("http://apps.nfc.gov.in/MailService/SendMail");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                InputStream in = response.getEntity().getContent();
                result = IOUtils.toString(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String sendMailWithAttachment(List<String> recipients, List<String> recipientsCC, String subject, String body,
            List<MailAttachmentParameters> attachmentList) {
        JSONObject json = new JSONObject();
        String result = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            if (Constants.ENV.equals(Constants.TESTING)) {

                if (recipientsCC != null) {
                    recipientsCC.clear();
                }

                recipients.clear();
                recipients.add(Constants.TESTMAILID);
            }
            json.put("recipients", recipients);
            json.put("recipientsCC", recipientsCC);
            json.put("subject", subject);
            json.put("body", body);
            json.put("senderMaidId", "noreply@nfc.gov.in");
            json.put("senderPassWord", "no@reply");
            json.put("attachmentList", attachmentList);
            StringEntity params = new StringEntity(json.toString());

            HttpPost request = new HttpPost("http://apps.nfc.gov.in/MailService/SendMailWithAttachments");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                InputStream in = response.getEntity().getContent();
                result = IOUtils.toString(in);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String sendSMS(List<String> recipients, String text) {
        JSONObject json = new JSONObject();
        String result = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            json.put("recipients", recipients);
            json.put("subject", text);
            json.put("applicationName", "VIMS");
            StringEntity params = new StringEntity(json.toString());

            HttpPost request = new HttpPost("http://apps.nfc.gov.in/CommonAPIs/SMS/SendSms");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                InputStream in = response.getEntity().getContent();
                result = IOUtils.toString(in);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String sendMailWithHTMLBody(List<String> recipients, List<String> recipientsCC, String subject, String body) {
        JSONObject json = new JSONObject();
        String result = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            if (Constants.ENV.equals(Constants.TESTING)) {
                recipients.clear();
                recipients.add(Constants.TESTMAILID);

                if (recipientsCC != null) {
                    recipientsCC.clear();
                }
            }

            json.put("recipients", recipients);
            json.put("recipientsCC", recipientsCC);
            json.put("subject", subject);
            json.put("body", body);
            json.put("senderMaidId", "noreply@nfc.gov.in");
            json.put("senderPassWord", "no@reply");
            StringEntity params = new StringEntity(json.toString());

            HttpPost request = new HttpPost("http://apps.nfc.gov.in/MailService/SendMailWithHTMLBody");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            
             try (CloseableHttpResponse response = httpClient.execute(request)) {
                InputStream in = response.getEntity().getContent();
                result = IOUtils.toString(in);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
