package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@NamedQueries({
		@NamedQuery(name="product.all", query="SELECT OBJECT(p) FROM Product p"),
		@NamedQuery(name="product.id", query="SELECT OBJECT(p) FROM Product p WHERE p.id =:productId"),
        })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	private String category;
	@OneToMany(mappedBy = "product")
	private List<Comment> comments = new ArrayList<Comment>();

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@XmlTransient
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
