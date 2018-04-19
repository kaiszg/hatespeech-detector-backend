/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 19.04.2018
 */
package de.beuthhochschule.hatespeech.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.beuthhochschule.hatespeech.api.model.Comment;
import de.beuthhochschule.hatespeech.api.repositories.CommentRepository;

/**
 *
 * @author Kais
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;

	@Override
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment createComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> createComments(List<Comment> comments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment deleteComment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
