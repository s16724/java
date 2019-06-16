package rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Comment;
import domain.Product;

@Path("/products")
@Stateless
public class ProductResources {

	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll() {
		return em.createNamedQuery("product.all", Product.class).getResultList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Product product) {
		em.persist(product);
		return Response.ok().build();
	}
	
	@GET
	@Path("/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("productId") int productId) {
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", productId)
				.getSingleResult();
		if (result == null) {
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}

	@PUT
	@Path("/{productId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("productId") int productId, Product p){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", productId)
				.getSingleResult();;
		if(result==null){
			return Response.status(404).build();
		}
		result.setName(p.getName());
		result.setPrice((p.getPrice()));
		result.setCategory(p.getCategory());
		em.persist(p);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{productId}")
	public Response delete(@PathParam("productId") int productId){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", productId)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		em.remove(result);
		return Response.ok().build();
	}

	@GET
	@Path("/{productId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@PathParam("productId") int productId){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", productId)
				.getSingleResult();
		if(result==null)
			return null;
		return result.getComments();
	}

	@POST
	@Path("/{productId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("productId") int productId, Comment comment){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", productId)
				.getSingleResult();
		if(result==null)
			return Response.status(404).build();
		result.getComments().add(comment);
		comment.setProduct(result);
		em.persist(comment);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{productId}/comments/{commentId}")
	public Response deleteComment(@PathParam("commentId") int commentId) {
		Comment result = em.createNamedQuery("comment.id", Comment.class)
				.setParameter("commentId", commentId)
				.getSingleResult();
		if (result == null) {
			return Response.status(404).build();
		}
		em.remove(result);
		return Response.ok().build();
	}

}
