package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.Model;

@Entity
public class Warehouse extends Model{
	
	@Id
	public Long id;
	public String name;
	
	@OneToMany(mappedBy="warehouse", cascade=CascadeType.ALL)
	public List<StockItem> stockitem = new ArrayList<>();
	
	public Warehouse(String name)
	{
		this.name = name;
	}
	
	public static Finder<Long, Warehouse> find(){
		return new Finder<>(Warehouse.class);
	}
}
