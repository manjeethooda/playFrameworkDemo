package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.Model;

@Entity
public class StockItem extends Model{
	
	@Id
	public Long id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Warehouse warehouse;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Product product;
	public long quantity;
	
	public StockItem(Warehouse warehouse, Product product, long quantity)
	{
		this.warehouse = warehouse;
		this.product = product;
		this.quantity = quantity;
	}
	
	public static Finder<Long, StockItem> find(){
		return new Finder<>(StockItem.class);
	}
}
