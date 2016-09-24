package modelTest;

import static org.junit.Assert.*;
import org.junit.Test;
import static play.test.Helpers.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.ImmutableMap;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;
import play.twirl.api.Content;

import models.Product;
import models.StockItem;
import models.Tag;
import models.Warehouse;


public class allModels {
	
	@Test
	public void testAllModel()
	{
		running(fakeApplication(inMemoryDatabase()), new Runnable(){
			
			@Override
			public void run()
			{
				//make an instance
				Product product = new Product("123","Macbook", "A laptop");
				Tag tag = new Tag("computer");
				Warehouse warehouse = new Warehouse("warehouse");
				StockItem stockItem = new StockItem(warehouse, product, 20);
				
				
				//save instance into db
				product.save();
				tag.save();
				warehouse.save();
				stockItem.save();
				
				//retrieve list from db
				List<Product> productList = Product.find().findList();
				List<Tag> tagList = Tag.find().findList();
				List<Warehouse> warehouseList = Warehouse.find().findList();
				List<StockItem> stockItemList = StockItem.find().findList();
				
				//check if fetched list is not empty
				assertEquals("Checking productList size", productList.size(), 1);
				assertEquals("Checking tagList size", tagList.size(), 1);
				assertEquals("Checking warehouseList size", warehouseList.size(), 1);
				assertEquals("Checking StockItemList size", stockItemList.size(), 1);
					
				//check if product retrieved is same as saved
				assertEquals("product-stockItem", productList.get(0).stockItems.get(0) , stockItemList.get(0));
				//assertEquals("product-tag", productList.get(0).tags.get(0), tagList.get(0).products.get(0));
				assertEquals("warehouse-stockItem", warehouseList.get(0).stockitem.get(0), stockItemList.get(0));
				//assertEquals("tag-product", tagList.get(0).products.get(0), productList.get(0).tags.get(0));
				assertEquals("stockItem-warehouse", stockItemList.get(0).warehouse, warehouseList.get(0));
				
				
				//do some ORM operations
				//change the saved product in db
				tag.products.clear();
				tag.save();
				
				//above operation should not affect already fetched list
				assertEquals("Previous tagList still has product", tagList.size(), 1);
				//but newly fetched list will not have any tag
				//assertEquals("fresh fetched tags list", Tag.find().findList().size(), 0);
				
				//delete all tags
				tag.delete();
				assertTrue("no tags ", Tag.find().findList().isEmpty());
				
			}
		});
	}

}
