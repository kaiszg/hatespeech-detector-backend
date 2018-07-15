/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 19.04.2018
 */
package de.beuthhochschule.hatespeech.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import de.beuthhochschule.hatespeech.api.model.Comment;
import de.beuthhochschule.hatespeech.api.model.CommentHourStatistic;

/**
 *
 * @author Kais
 *
 */
public interface CommentService {
	
	/**
	 * 
	 * @return
	 */
	public List<Comment> findAll();
	
	/**
	 * 
	 * @return
	 */
	public List<Comment> findAllByOrderScore();

	/**
	 *
	 * @param sortOrder
	 * @return
	 */
	public List<Comment> findLabelledComments(Optional<String> sortOrder);

	/**
	 *
	 * @param sortOrder
	 * @return
	 */
	public List<Comment> findUnlabelledComments(Optional<String> sortOrder);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Comment findById(Long id);
	
	/**
	 * 
	 * @param comment
	 * @return
	 */
	public Comment createComment(Comment comment);
	
	/**
	 * 
	 * @param comments
	 * @return
	 */
	public List<Comment> createComments(List<Comment> comments);
	
	/**
	 * 
	 * @param comment
	 * @return
	 */
	public Comment updateComment(Comment comment);
	
	/**
	 * 
	 * @param id
	 */
	public void deleteComment(Long id);

	/**
	 *
	 * @return
	 */
	public List<CommentHourStatistic> getNbDeletedPerHour();

	/**
	 *
	 * @return
	 */
	public int getNbDeletedComments();

	/**
	 *
	 * @param numberOfComments
	 * @return
	 */
	public List<Comment> findTopCommentsOfCurrentWeek(int numberOfComments);

	/**
	 *
	 * @return
	 */
	public int getNbNotDeletedComments();

	/**
	 *
	 * @return
	 */
	int getNbCommentsToday();

	/**
	 *
	 * @return
	 */
	int getNbCommentsThisWeek();

	/**
	 * 
	 * @param fromDate
	 * @return
	 */
	public List<Comment> findAllFromDateByOrderScore(Date fromDate);

	/**
	 * 
	 * @param fromDate
	 * @return
	 */
	public List<Comment> findLabelledFromDateByOrderScore(Date fromDate);

	/**
	 * 
	 * @param fromDate
	 * @return
	 */
	public List<Comment> findUnlabelledFromDateByOrderScore(Date fromDate);
}
