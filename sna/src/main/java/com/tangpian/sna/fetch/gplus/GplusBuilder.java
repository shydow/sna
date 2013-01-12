package com.tangpian.sna.fetch.gplus;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;

import org.springframework.core.io.Resource;

import com.google.api.client.auth.security.PrivateKeys;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusScopes;

public class GplusBuilder {
	
	private Resource privateKeyFile;
	private String privateKeyType;
	private String serviceAccountEmail;
	
	public Resource getPrivateKeyFile() {
		return privateKeyFile;
	}

	public void setPrivateKeyFile(Resource privateKeyFile) {
		this.privateKeyFile = privateKeyFile;
	}

	public String getPrivateKeyType() {
		return privateKeyType;
	}

	public void setPrivateKeyType(String privateKeyType) {
		this.privateKeyType = privateKeyType;
	}

	public String getServiceAccountEmail() {
		return serviceAccountEmail;
	}

	public void setServiceAccountEmail(String serviceAccountEmail) {
		this.serviceAccountEmail = serviceAccountEmail;
	}

	private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static JsonFactory JSON_FACTORY = new JacksonFactory();
	private static Plus plus;
	
	public Plus getServicePlus() throws GeneralSecurityException,
			IOException {

		if (null == plus) {
			PrivateKey key = PrivateKeys.loadFromKeyStore(
					KeyStore.getInstance(privateKeyType),privateKeyFile.getInputStream(),
					"notasecret", "privatekey", "notasecret");

			GoogleCredential credential = new GoogleCredential.Builder()
					.setTransport(HTTP_TRANSPORT).setJsonFactory(JSON_FACTORY)
					.setServiceAccountId(serviceAccountEmail)
					.setServiceAccountScopes(PlusScopes.PLUS_ME)
					.setServiceAccountPrivateKey(key).build();

			plus = new Plus.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
					.setApplicationName("gplustrend").build();
		}
		return plus;
	}

	
//	/** Global instance of the HTTP transport. */
//	public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
//
//	/** Global instance of the JSON factory. */
//	public static final JsonFactory JSON_FACTORY = new JacksonFactory();
//
//	// OAuth client ID
//	private static String CLIENT_ID = FetcherUtil
//			.getProperty("oauth_client_id");
//
//	// OAuth client secret
//	private static String CLIENT_SECRET = FetcherUtil
//			.getProperty("oauth_client_secret");
//
//	// OAuth client secret
//	public static String GOOGLE_API_KEY = FetcherUtil
//			.getProperty("google_api_key");
//
//	// Space separated list of OAuth scopes
//	private static final List<String> SCOPES = Arrays
//			.asList("https://www.googleapis.com/auth/plus.me");
//
//	// OAuth redirect URI
//	public static String REDIRECT_URI = FetcherUtil
//			.getProperty("oauth_redirect_uri");
//
//	private static final String SERVICE_ACCOUNT_EMAIL = "517743315265@developer.gserviceaccount.com";
//	private static Plus plus = null;
//
//	public static Plus getServicePlus() throws GeneralSecurityException,
//			IOException {
//
//		if (null == plus) {
//			PrivateKey key = PrivateKeys.loadFromKeyStore(
//					KeyStore.getInstance("PKCS12"),
//					GplusFactory.class.getResourceAsStream("/key.p12"),
//					"notasecret", "privatekey", "notasecret");
//
//			GoogleCredential credential = new GoogleCredential.Builder()
//					.setTransport(HTTP_TRANSPORT).setJsonFactory(JSON_FACTORY)
//					.setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
//					.setServiceAccountScopes(PlusScopes.PLUS_ME)
//					.setServiceAccountPrivateKey(key).build();
//
//			plus = new Plus.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
//					.setApplicationName("gplustrend").build();
//		}
//		return plus;
//	}
//
//	private static AuthorizationCodeFlow authorizationCodeFlow = null;
//	private static final String GOOGLE_OAUTH2_AUTH_URI = "https://accounts.google.com/o/oauth2/auth";
//	private static final String GOOGLE_OAUTH2_TOKEN_URI = "https://accounts.google.com/o/oauth2/token";
//
//	public static AuthorizationCodeFlow getClientFlow() {
//		GoogleClientSecrets clientSecrets;
//		try {
//			clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
//					GplusFactory.class
//							.getResourceAsStream("/client_secrets.json"));
//			if (null == authorizationCodeFlow) {
//				authorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(
//						GplusFactory.HTTP_TRANSPORT, GplusFactory.JSON_FACTORY,
//						clientSecrets, SCOPES).setAccessType("offline")
//						.setApprovalPrompt("force").build();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return authorizationCodeFlow;
//	}
//
//	public static String stripTags(String input) {
//		return input.replaceAll("\\<[^>]*>", "");
//	}

}
