package zomatoAPI_Automation;

import java.util.Iterator;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class Find_restaurant_in_Jhansi {

	@Test()
	public void ListofRestaurant() {
		String CityName = "jhansi";
		RestAssured.baseURI = "https://developers.zomato.com/api/v2.1/";
		ValidatableResponse response = RestAssured.given().header("user-key", "a9a8b860396953c3de905e9ad8283dff").when().get("cities?q="+CityName)
				.then().body("location_suggestions.name", Matchers.contains("Jhansi"));
		response.assertThat().statusCode(200);
		
		Response resp = RestAssured.given().header("user-key", "a9a8b860396953c3de905e9ad8283dff").when().get("cities?q="+CityName);
		Object CityID = resp.body().jsonPath().get("location_suggestions.id");
		
		String city = CityID.toString().replaceAll("[\\[\\]]", "");
		Response restaurants = RestAssured.given().header("user-key", "a9a8b860396953c3de905e9ad8283dff").when().get("location_details?entity_id="+city+"&entity_type=city");
		
		JsonPath temp = restaurants.jsonPath().setRoot("best_rated_restaurant.restaurant");
		List<Object> address = temp.getList("location.address");
		Iterator<Object> addcounter = address.iterator();
		List<Object> Rest_Name = temp.getList("name");
		
		System.out.println("Total Number of Restaurant Avaible :: "+Rest_Name.size());
		
		Iterator<Object> counter = Rest_Name.iterator();
		while(counter.hasNext())
		{
			System.out.println("Restaurant Name :: "+counter.next().toString());	
			System.out.println("Address :: "+addcounter.next().toString());
		}
		
	}
}
