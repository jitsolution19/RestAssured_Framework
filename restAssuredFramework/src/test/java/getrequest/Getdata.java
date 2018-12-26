package getrequest;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Getdata {

	@Test
	public void testresponsecode() {
		Response resp = RestAssured.get(
				"https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		int code = resp.getStatusCode();
		System.out.println("Status Code " + code);
		Assert.assertEquals(code, 200);
	}
	
	@Test
	public void Sessionid()
	{
		Response temp =RestAssured.get(
				"https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");		
		String Sessionid =temp.getSessionId();
		System.out.println("Session ID "+Sessionid);
		System.out.println("Body :: "+temp.body());		
		System.out.println("Body XML Path :: "+temp.body().xmlPath());
		System.out.println("getSessionId "+temp.getSessionId());
		System.out.println("getStatusLine "+temp.getStatusLine());
		System.out.println("Time "+temp.getTime());
		System.out.println("getContentType "+temp.getContentType());
		System.out.println("detailedCookies "+temp.detailedCookies());
		System.out.println("contentType "+temp.contentType());
		System.out.println("getTimeIn "+temp.getTimeIn(TimeUnit.MILLISECONDS));
	}
	
	@Test
	public void tbody()
	{
		ResponseBody<?>  temp = RestAssured.get(
				"https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").getBody();
		JsonPath  path = temp.jsonPath();
		System.out.println("Json Path od Response : "+path);
		System.out.println(temp);
	}
	
	@Test
	public void getinformation()
	{
		String text = RestAssured.get("http://restcountries.eu/rest/v1").asString();
		System.out.println(text);
	}
	
	@Test
	public void lotto_resource_returns_200_with_expected_id_and_winners()
	{
		System.out.println(RestAssured.get("http://restcountries.eu/rest/v1?name=Afghanistan").asString());
	}
}
