package zomatoAPI_Automation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class Setup {

	@Test
	public void zomatoSetup()
	{
		RestAssured.baseURI= "https://developers.zomato.com/api/v2.1/";
		Header someHeader = new Header("user-key","a9a8b860396953c3de905e9ad8283dff");
		System.out.println(RestAssured.given().header(someHeader).when().get("cuisines?city_id=5").getStatusCode());
		Response request = RestAssured.given().header(someHeader).when().get("cuisines?city_id=5");
		System.out.println(request.body().asString());
		System.out.println(request.contentType());
		System.out.println(request.getSessionId());
		System.out.println(request.getStatusLine());
		System.out.println(request.getTime());
		System.out.println(request.cookies());
		System.out.println(request.getHeaders());		
	}
	
	@Test
	public void searchCity()
	{
		RestAssured.baseURI= "https://developers.zomato.com/api/v2.1/";
		Response repvalue = RestAssured.given().header("user-key","a9a8b860396953c3de905e9ad8283dff").when().get("cities?q=jhansi");
		System.out.println(repvalue.body());
		System.out.println(repvalue.getBody().asString());
		System.out.println("City ID :: "+repvalue.body().jsonPath().get("location_suggestions.id"));
	}
}

