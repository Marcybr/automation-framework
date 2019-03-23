package br.com.VH.framework.tests.API;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;

public class CurrencyAPI {
	
	@Parameters("currencyTypeBRL")
	@Test
	public void testBRLcurrency(String currencyTypeBRL) {
	         
	    given().
	        params("text", currencyTypeBRL).
	    when().
	        get("https://api.exchangeratesapi.io/latest").
	    then().
	        assertThat().
	        	body("$", Matchers.anything(currencyTypeBRL));
	}
	
	@Parameters("currencyTypeCAD")
	@Test
	public void testCADcurrency(String currencyTypeCAD) {
	         
	    given().
	        params("text", currencyTypeCAD).
	    when().
	        get("https://api.exchangeratesapi.io/latest").
	    then().
	        assertThat().
	        	body("$", Matchers.anything(currencyTypeCAD));
	}

}
