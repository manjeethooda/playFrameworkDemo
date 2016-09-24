package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.Model;
import play.data.validation.Constraints.Required;

@Entity
public class Tag extends Model{
	
	@Id
	public Long primaryKey;
	
	@Required
	public String tagId;
	
	@ManyToMany(mappedBy="tags", cascade=CascadeType.ALL)
	public List<Product> products = new ArrayList<>();
		
	public Tag(String tagId)
	{
		this.tagId = tagId;
	}
	
	public static Finder<Long, Tag> find(){
		return new Finder<>(Tag.class);
	}
	
	public String toString()
	{
		return String.format("[Tag %s]", tagId);
	}
	
	
	//disable any tag to be named tagId
	public String validate()
	{
		return ("Tag".equals(this.tagId)) ? "Invalid tag name" : null;
	}
}
