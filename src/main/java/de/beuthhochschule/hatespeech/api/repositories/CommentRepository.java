/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 19.04.2018
 */
package de.beuthhochschule.hatespeech.api.repositories;

import de.beuthhochschule.hatespeech.api.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

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
	
	@Query("FROM Comment c WHERE c.timestamp >= :fromDate ORDER BY c.score DESC")
	List<Comment> findAllFromDateByOrderScore(@Param("fromDate") Date fromDate);

	@Query("FROM Comment c WHERE c.timestamp >= :fromDate AND c.label IS NOT NULL ORDER BY c.score DESC")
	List<Comment> findLabelledFromDateByOrderScore(@Param("fromDate") Date fromDate);

	@Query("FROM Comment c WHERE c.timestamp >= :fromDate AND c.label IS NULL ORDER BY c.score DESC")
	List<Comment> findUnlabelledFromDateByOrderScore(@Param("fromDate") Date fromDate);

	@Query("FROM Comment c WHERE c.timestamp BETWEEN :date1 AND :date2 ORDER BY c.score DESC")
	List<Comment> findUnlabelledBetweenDates(@Param("date1") Date date1, @Param("date2") Date date2, Pageable pageable);

	@Query("SELECT DISTINCT c.subLabel FROM Comment c WHERE c.subLabel IS NOT NULL")
	List<String> findAllSubLabels();

	@Query("SELECT COUNT(c) FROM Comment c WHERE (hour(c.timestamp) = :hour) AND (c.label = :label)")
	int getNbCommentsForHourAndLabel(@Param("hour") int hour, @Param("label") String label);

	@Query("SELECT COUNT(c) FROM Comment c WHERE (day(c.timestamp) = day(:date)) AND (month(c.timestamp) = month(:date)) AND (year(c.timestamp) = year(:date))")
	int countCommentsOfDate(@Param("date") Date date);

	@Query("SELECT count(c) FROM Comment c WHERE c.timestamp BETWEEN :date1 AND :date2")
	int countCommentsBetweenDates(@Param("date1") Date date1, @Param("date2") Date date2);

	int countByLabel(String label);

	int countBySubLabel(String subLabel);

}
