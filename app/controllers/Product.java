package controllers;

import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.*;
import play.mvc.*;
import play.mvc.Result;
import play.data.Form.*;
import play.data.FormFactory;
import static play.data.Form.*;
import play.api.data.*;
import play.api.data.Forms.*;
import javax.inject.*;


public class Product extends Controller {
	
	public Result index()
	{
		List<models.Product> products = models.Product.find().findList();
		return ok(products.isEmpty() ? "No Product" : products.toString());
	}
	
	public Result details(String productId)
	{
		models.Product product = models.Product.find().where().eq("productId", productId).findUnique();
		return (product != null) ? ok(product.toString()) : notFound("No product Found"); 
	}
	
	@Inject FormFactory formFactory;
	public Result create()
	{
		Form<models.Product> productForm = formFactory.form(models.Product.class);
		models.Product product = productForm.bindFromRequest().get();
		if(!productForm.hasErrors())
		{
			//return badRequest(productCreate.render(productForm));
		}
		
		product.save();
		//return ok(productCreate.render(productForm));
		return redirect(routes.HomeController.index());
	}
	
	public Result delete(String productId)
	{
		models.Product product = models.Product.find().where().eq("productId", productId).findUnique();
		if(product != null)
		{
			product.delete();
		}
		return ok();
	}
}
