package step_definitions;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import pojo.AddPlace;
import pojo.Location;

public class Step_Definition {
	RequestSpecification res;
	ResponseSpecification resspec ;
	Response response ;
	String responseString;
	@Given("Add placePayload")
	public void add_place_payload() {
		RestAssured.baseURI="https://rahulshettyacademy.com";

		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);

		 RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		 
		 
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res=given().spec(req)
		.body(p);
	}
	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) {
		response =res.when().post("/maps/api/place/add/json").
				then().spec(resspec).extract().response();
	    
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int statuscode) {
		responseString=response.asString();
		System.out.println(responseString);
		assertEquals(response.getStatusCode(),statuscode);
	   
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) {
	    JsonPath js =new JsonPath(responseString);
	    assertEquals(js.get(keyvalue).toString(),expectedvalue);
	}


}
