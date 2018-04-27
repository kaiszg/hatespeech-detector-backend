/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 19.04.2018
 */
package de.beuthhochschule.hatespeech.api.services;

import java.util.List;

import de.beuthhochschule.hatespeech.api.model.Comment;

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
	 * @return
	 */
	public List<Comment> findLabelledCommentsOrderByScore();
	
	/**
	 * 
	 * @return
	 */
	public List<Comment> findUnlabelledCommentsOrderByScore();
	
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

}
