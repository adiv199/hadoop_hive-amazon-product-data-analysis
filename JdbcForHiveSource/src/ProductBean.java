import java.util.HashMap;


public class ProductBean {
	
	 String asin;
	 String description;
	 String title;
	 RelatedBean related;
	 Double price;
	 HashMap<String,Integer> salesrank;
	 String imurl;
	 String categories;
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public RelatedBean getRelated() {
		return related;
	}
	public void setRelated(RelatedBean related) {
		this.related = related;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public HashMap<String, Integer> getSalesrank() {
		return salesrank;
	}
	public void setSalesrank(HashMap<String, Integer> salesrank) {
		this.salesrank = salesrank;
	}
	public String getImurl() {
		return imurl;
	}
	public void setImurl(String imurl) {
		this.imurl = imurl;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}

	 
}
