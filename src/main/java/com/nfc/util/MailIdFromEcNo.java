package com.nfc.util;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author NFC
 */
public class MailIdFromEcNo {

	public String getMailIdFromEcNo(int ecNo) {
		String result = null;
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpGet request = new HttpGet("http://apps.nfc.gov.in/LDAPService/mailId?ecNo=" + ecNo);

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
