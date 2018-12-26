package zomatoAPI_Automation;

import java.util.Iterator;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

public class Find_restaurant {

	@Test
	public void findrestaurantInPune() {
		RestAssured.baseURI = "https://developers.zomato.com/api/v2.1/";
		ResponseBody<?> rep = RestAssured.given().header("user-key", "a9a8b860396953c3de905e9ad8283dff").when()
				.get("location_details?entity_id=5&entity_type=city").body();
		System.out.println(rep.asString());
		List<String> con = rep.jsonPath().getList("best_rated_restaurant.restaurant.name");
		System.out.println(con.size());
		Iterator<String> counter = con.iterator();
		while (counter.hasNext()) {
			System.out.println(counter.next());
			Reporter.log("Resturant name are as "+counter.next().toString()+"\n");
		}
		
		ValidatableResponse resp = RestAssured.given().header("user-key", "a9a8b860396953c3de905e9ad8283dff").when()
		.get("location_details?entity_id=5&entity_type=city").then();
		resp.body("city", Matchers.notNullValue());	
		resp.body("city", Matchers.equalTo("Pune"));
		Reporter.getCurrentTestResult().getName();
	}
	
	@Test
	public void findresturantUsingParm()
	{
		RestAssured.baseURI = "https://developers.zomato.com/api/v2.1/";
		ValidatableResponse response = RestAssured.given().header("user-key", "a9a8b860396953c3de905e9ad8283dff").when().get("location_details?entity_id=5&entity_type=city").then().body("city", Matchers.equalTo("Pune"));
		System.out.println(response.toString());
		Reporter.log(response.toString(),true);
		response.statusCode(200);
		response.contentType("application/json");
//		response.body(arguments, responseAwareMatcher)(parser)
	}

}
