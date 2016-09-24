package modelTest;

import static org.junit.Assert.*;
import org.junit.Test;
import static play.test.Helpers.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;
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

public class productTest {
	
	@Test
	public void testProductModel()
	{
		running(fakeApplication(inMemoryDatabase()), new Runnable(){
			
			@Override
			public void run()
			{
				//make an instance
				String productId = "123";
				String name = "Macbook";
				String description = "A laptop";
				Product product = new Product(productId, name, description);
				
				//save instance into db
				product.save();
				
				//retrieve list from db
				List<Product> productList = Product.find().findList();
				
				//check if fetched list is not empty
				assertEquals("Checking productList size", productList.size(), 1);
				
				//check if product retrieved is same as saved
				assertEquals("Comparing product name", productList.get(0).name , name);
				assertEquals("Comparing product description", productList.get(0).description , description);
				assertEquals("Comparing product Id", productList.get(0).productId , productId);				
			}
		});
	}

}
