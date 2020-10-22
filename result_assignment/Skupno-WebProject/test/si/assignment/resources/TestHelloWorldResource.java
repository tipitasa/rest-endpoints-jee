package si.assignment.resources;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class TestHelloWorldResource {
	
	final static String BASE_URL = "http://localhost:8080/Skupno-WebProject/rest";
	final static String APPLICATION_JSON = "application/json";
	
//	private static EntityManager em = null;
//	
//	@BeforeClass
//    public static void setUpClass() throws Exception {
//        // TODO try to get this to work
//        if (em == null) {
//            em = (EntityManager) Persistence.createEntityManagerFactory("Skupno-WebProject").createEntityManager();
//        }
//    }

	/**
	 * Sucess on getHelloWorld
	 * Returns 200 OK
	 * Returns correct header type
	 */
	@Test
	public void testGetHelloWorld() {
		URL url;
		StringBuffer content;
		HttpURLConnection con = null;
		try {
			url = new URL(BASE_URL + "/helloWorld");
			con = (HttpURLConnection) url.openConnection();

			con.setRequestProperty("Content-Type", APPLICATION_JSON);
			con.setRequestMethod("GET");

			// make a request and get a status code
			con.connect();
			int status = con.getResponseCode();
			assertEquals(200, status);

			// assert correct content
			content = getStream(con);
			assertEquals("Hello world!", content.toString());

			// assert returned header
			String contentType = con.getHeaderField("Content-Type");
			assertEquals(APPLICATION_JSON, contentType);

		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	
	/**
	 * 415 on getHelloWorld
	 * Returns 415 UnsuportedMediaType
	 * Returns correct header type
	 */
	@Test
	public void testGetHelloWorld_UnsuportedMediaType() {
		URL url;
		HttpURLConnection con = null;
		int status = 0;
		try {
			url = new URL(BASE_URL + "/helloWorld");
			con = (HttpURLConnection) url.openConnection();

			con.setRequestProperty("Content-Type", "application/xml");
			con.setRequestMethod("GET");

			// make a request and get a status code
			con.connect();
			status = con.getResponseCode();
			// goes to exception at this step

		} catch (Exception e) {
			assertEquals(415, status);
		} finally {
			if (con != null) {
				assertEquals(415, status);
				con.disconnect();
			}
		}
	}

	/**
	 * Success on saveTheDate
	 * Returns 200 OK
	 * Returns correct header type
	 */
	@Test
	public void testSaveTheDate() {
		URL url;
		StringBuffer content;
		HttpURLConnection con = null;
		try {
			String dateToSave = "2020-03-18";
			url = new URL(BASE_URL + "/helloWorld/" + dateToSave);
			con = (HttpURLConnection) url.openConnection();
			
			con.setRequestProperty("Content-Type", APPLICATION_JSON);
			con.setRequestMethod("POST");
			
			// make a request
			con.connect();
			
			// check the status code
			int status = con.getResponseCode();
			assertEquals(201, status); // created
			
			// delete object
			content = getStream(con);
			String createdId = content.toString();
						
			url = new URL(BASE_URL + "/helloWorld/delDate/" + createdId);
			con = (HttpURLConnection) url.openConnection();
			
			con.setRequestProperty("Content-Type", APPLICATION_JSON);
			con.setRequestMethod("DELETE");
			
			// make a request
			con.connect();
			status = con.getResponseCode();
			assertEquals(204, status); // created
			
		} catch (IOException e) {
			fail();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}

	
	/**
	 * Bad request on saveTheDate
	 * Returns 403 FORBIDDEN
	 * Returns correct header type
	 */
	@Test
	public void testSaveTheDate_403_FORBIDDEN() {
		URL url;
		HttpURLConnection con;
		int status = 0;
		try {
			String dateToSave = "20200318";
			url = new URL(BASE_URL + "/helloWorld/" + dateToSave);
			con = (HttpURLConnection) url.openConnection();
			
			con.setRequestProperty("Content-Type", APPLICATION_JSON);
			con.setRequestMethod("POST");
			
			// make a request
			con.connect();
			
			// check the status code
		    status = con.getResponseCode();
						
		} catch (IOException e) {
			assertEquals(400, status); // created
		} 
	}

	private StringBuffer getStream(HttpURLConnection con) throws IOException {
		InputStream inputStream = con.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader in = new BufferedReader(inputStreamReader);
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		return content;
	}

}
