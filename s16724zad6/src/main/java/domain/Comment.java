package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQuery(name="comment.id", query="SELECT OBJECT(c) FROM Comment c WHERE c.id =:commentId")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String comment;
	@ManyToOne
	private Product product;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct(){
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
