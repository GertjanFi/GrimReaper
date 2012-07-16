package nl.knaw.huygens.grim.traversal;

public interface IActionHandler<T> {
	
	public void act(T entity);
	
	public boolean verify(T entity);

}
