package nl.knaw.huygens.grim.traversal;

import java.util.List;

import nl.knaw.huygens.grim.model.Entity;

public interface ITraversal<T extends Entity> {

	List<T> run(String rootName);

}
