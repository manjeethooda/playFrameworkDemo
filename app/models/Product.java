package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import play.db.ebean.*;
import com.avaje.ebean.Model;
import play.data.validation.Constraints.Required;

@Entity
public class Product extends Model{
	
	@Id
	public Long primaryKey;
	
	@Required
	public String productId;
	
	@Required
	public String name;
	public String description;
	
	@ManyToMany(cascade=CascadeType.ALL)
	public List<Tag> tags = new ArrayList<>();
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	public List<StockItem> stockItems = new ArrayList<>();
	
	public Product(String productId, String name, String description)
	{
		this.productId = productId;
		this.name = name;
		this.description = description;
	}
	
	public static Finder<Long, Product> find(){
		return new Finder<>(Product.class);
	}
	
	public String toString()
	{
		return String.format("[Product %s %s %s]", productId, name, description);
	}
}
