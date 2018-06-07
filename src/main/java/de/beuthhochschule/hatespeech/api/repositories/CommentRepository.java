/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 19.04.2018
 */
package de.beuthhochschule.hatespeech.api.repositories;

import java.util.List;

import de.beuthhochschule.hatespeech.api.model.CommentHourStatistic;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.beuthhochschule.hatespeech.api.model.Comment;
import org.springframework.data.repository.query.Param;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author Kais
 *
 */
public interface CommentRepository extends CrudRepository<Comment, Long>{
	List<Comment> findAllByOrderByScoreAsc();

	List<Comment> findByLabelNotNullOrderByScoreDesc();

	List<Comment> findByLabelNotNullOrderByArticleIdDescScoreDesc();

	List<Comment> findByLabelNotNullOrderByTimestampDescScoreDesc();

	List<Comment> findByLabelIsNullOrderByScoreDesc();

	List<Comment> findByLabelIsNullOrderByArticleIdDescScoreDesc();

	List<Comment> findByLabelIsNullOrderByTimestampDescScoreDesc();

	@Query("SELECT DISTINCT c.subLabel FROM Comment c WHERE c.subLabel IS NOT NULL")
	List<String> findAllSubLabels();

	@Query("SELECT COUNT(c) FROM Comment c WHERE (hour(c.timestamp) = :hour) AND (c.label = :label)")
	int getNbCommentsForHourAndLabel(@Param("hour") int hour, @Param("label") String label);

	int countByLabel(String label);

	int countBySubLabel(String subLabel);
}
