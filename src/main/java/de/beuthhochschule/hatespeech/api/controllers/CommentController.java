/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 25.04.2018
 */
package de.beuthhochschule.hatespeech.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.beuthhochschule.hatespeech.api.model.Comment;
import de.beuthhochschule.hatespeech.api.services.CommentService;

/**
 *
 * @author Kais
 *
 */
@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public List<Comment> getAllComments() {
		return commentService.findAllByOrderScore();
	}

	@GetMapping("/labelled")
	public List<Comment> getLabelledComments() {
		return commentService.findLabelledCommentsOrderByScore();
	}
	
	@GetMapping("/unlabelled")
	public List<Comment> getUnlabelledComments() {
		return commentService.findUnlabelledCommentsOrderByScore();
	}
	
	@PostMapping
	public Comment createComment(@RequestBody Comment comment) {
		return commentService.createComment(comment);
	}
}
