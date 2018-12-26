package zomatoAPI_Automation;

import java.util.Iterator;
import java.util.List;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class ModifiedSetup {
	
	@Test
	public void Setup() {
		RestAssured.baseURI = "https://developers.zomato.com/api/v2.1/";
		int statuscode = RestAssured.given().header("user-key", "a9a8b860396953c3de905e9ad8283dff").when()
				.get("cuisines?city_id=5").getStatusCode();
		if (statuscode == 200)
			System.out.println("Pass");
		else
			System.out.println("Fail");
	}
	
	@Test
	public void getexpirydate()
	{
		RestAssured.baseURI = "https://developers.zomato.com/api/v2.1/";
		ResponseBody<?> rep = RestAssured.given().header("user-key", "a9a8b860396953c3de905e9ad8283dff").when()
				.get("cuisines?city_id=5").body();
		System.out.println(rep.jsonPath());
		System.out.println(rep.asString());
		List<String> con = rep.jsonPath().getList("cuisines.cuisine.cuisine_name");
		System.out.println(con.size());
		Iterator<String> counter = con.iterator();
		while(counter.hasNext())
		{
			System.out.println(counter.next());
		}
	}
	
}