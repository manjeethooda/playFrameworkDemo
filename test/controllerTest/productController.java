package controllerTest;

import static org.junit.Assert.*;
import org.junit.Test;
import static play.test.Helpers.*;
import static play.mvc.Http.Status.*;
import static play.test.Helpers.*;
import play.mvc.Http.RequestBuilder;
import models.Product;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.ImmutableMap;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;
import play.twirl.api.Content;

public class productController {
	
	@Test
	public void testProductModel()
	{
		running(fakeApplication(inMemoryDatabase()), new Runnable(){
			
			@Override
			public void run()
			{
				//get result on empty db
				RequestBuilder request = new RequestBuilder()
			            .method(GET)
			            .uri("/products");
				
				Result result = route(request);
			    assertTrue("Empty Products", contentAsString(result).contains("No Product"));
				
				//make an instance
				String productId = "123";
				String name = "Macbook";
				String description = "A laptop";
				Product product = new Product(productId, name, description);
				
				//save instance into db
				product.save();
				
				//test GET on single product
				result = route(request);
			    assertTrue("One Product", contentAsString(result).contains(name));
				
				//check if fetched list is not empty
			}
		});
	}

}
