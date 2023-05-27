package cookies;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CookiesJavaHTTPClient
{
	public static void main(String[] args)
	{
		try
		{
			URL url = new URL("https://the-internet.herokuapp.com/authenticate");
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
// Set the necessary headers
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
// Enable input and output streams
			connection.setDoOutput(true);
			connection.setDoInput(true);
// Define the payload
			String payload = "username=tomsmith&password=SuperSecretPassword!";
// Write the payload to the request body
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(payload.getBytes());
			outputStream.flush();
			outputStream.close();
			
			// Get the response code
			int responseCode = connection.getResponseCode();
			// Read the response
			BufferedReader reader;
			if (responseCode == HttpURLConnection.HTTP_OK)
			{
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			}
			else
			{
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			}
			// Read the response line by line
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null)
			{
				response.append(line);
			}
			reader.close();
			// Print the response
			System.out.println("Response: " + response.toString());
			// Get the cookies from the response
			String cookies = connection.getHeaderField("Set-Cookie");
			System.out.println("Cookies: " + cookies);
			// Close the connection
			connection.disconnect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
