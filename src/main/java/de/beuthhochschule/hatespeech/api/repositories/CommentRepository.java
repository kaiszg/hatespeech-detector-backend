/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 19.04.2018
 */
package de.beuthhochschule.hatespeech.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.beuthhochschule.hatespeech.api.model.Comment;

/**
 *
 * @author Kais
 *
 */
public interface CommentRepository extends CrudRepository<Comment, Long>{
	List<Comment> findAllByOrderByScoreAsc();

	List<Comment> findByLabelNotNullOrderByScoreDesc();
	
	List<Comment> findByLabelIsNullOrderByScoreDesc();

	@Query("SELECT DISTINCT c.label FROM Comment c WHERE c.label IS NOT NULL")
	List<String> findAllLabels();

	int countByLabel(String label);
}
