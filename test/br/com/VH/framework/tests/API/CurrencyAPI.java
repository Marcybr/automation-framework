package br.com.VH.framework.tests.API;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;

public class CurrencyAPI {
	
	@Parameters("currencyType")
	@Test
	public void test(String currencyType) {
	         
	    given().
	        params("text", currencyType).
	    when().
	        get("https://api.exchangeratesapi.io/latest").
	    then().
	        assertThat().
	        	body("$", Matchers.anything(currencyType));
	}

}
