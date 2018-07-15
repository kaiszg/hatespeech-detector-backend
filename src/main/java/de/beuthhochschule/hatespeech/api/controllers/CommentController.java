/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 25.04.2018
 */
package de.beuthhochschule.hatespeech.api.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.beuthhochschule.hatespeech.api.model.Comment;
import de.beuthhochschule.hatespeech.api.model.CommentHourStatistic;
import de.beuthhochschule.hatespeech.api.services.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Kais
 */
@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {

	@Autowired
	private CommentService commentService;

	@ApiOperation(value = "Returns all the comments")
	@ApiResponses(
			value = {
					@ApiResponse(code = 204, message = "No content available")
			}
			)
	@GetMapping
	public ResponseEntity<List<Comment>> getAllComments() {
		List<Comment> comments = commentService.findAllByOrderScore();
		if (comments == null || comments.isEmpty()) {
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Returns all the comments after a specific date")
	@ApiResponses(
			value = {
					@ApiResponse(code = 204, message = "No content available")
			}
			)
	@GetMapping("/from-date")
	public ResponseEntity<List<Comment>> getAllCommentsAfterDate(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date fromDate) {
		List<Comment> comments = commentService.findAllFromDateByOrderScore(fromDate);
		if (comments == null || comments.isEmpty()) {
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Returns the labelled comments after a specific date")
	@ApiResponses(
			value = {
					@ApiResponse(code = 204, message = "No content available")
			}
			)
	@GetMapping("/labelled-from-date")
	public ResponseEntity<List<Comment>> getLabelledCommentsAfterDate(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date fromDate) {
		List<Comment> comments = commentService.findLabelledFromDateByOrderScore(fromDate);
		if (comments == null || comments.isEmpty()) {
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Returns the unlabelled comments after a specific date")
	@ApiResponses(
			value = {
					@ApiResponse(code = 204, message = "No content available")
			}
			)
	@GetMapping("/unlabelled-from-date")
	public ResponseEntity<List<Comment>> getUnlabelledCommentsAfterDate(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date fromDate) {
		List<Comment> comments = commentService.findUnlabelledFromDateByOrderScore(fromDate);
		if (comments == null || comments.isEmpty()) {
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

	@ApiOperation(value = "Returns the labelled comments")
	@ApiResponses(
			value = {
					@ApiResponse(code = 204, message = "No content available")
			}
			)
	@GetMapping({ "/labelled", "/labelled/{sortOrder}" })
	public ResponseEntity<List<Comment>> getLabelledComments(@ApiParam(value = "Optional sort order. Possible values: \"date\" to sort by date or \"article_id\" to sort by article") @PathVariable Optional<String> sortOrder) {
		List<Comment> comments = commentService.findLabelledComments(sortOrder);
		if (comments == null || comments.isEmpty()) {
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

	@ApiOperation(value = "Returns the unlabelled comments")
	@ApiResponses(
			value = {
					@ApiResponse(code = 204, message = "No content available")
			}
			)
	@GetMapping({ "/unlabelled", "/unlabelled/{sortOrder}" })
	public ResponseEntity<List<Comment>> getUnlabelledComments(@PathVariable Optional<String> sortOrder) {
		List<Comment> comments = commentService.findUnlabelledComments(sortOrder);
		if (comments == null || comments.isEmpty()) {
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

	@ApiOperation(value = "Returns the 5 comments the highest scores on the current week")
	@ApiResponses(
			value = {
					@ApiResponse(code = 204, message = "No content available")
			}
			)
	@GetMapping("/unlabelled/top-5-current-week")
	public ResponseEntity<List<Comment>> getTopFiveUnlabelledCommentsCurrentWeek() {
		List<Comment> comments = commentService.findTopCommentsOfCurrentWeek(5);
		if (comments == null || comments.isEmpty()) {
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

	@ApiOperation(value = "Returns the number of comments for the current day")
	@GetMapping("/nb-today")
	public int getNbCommentsToday() {
		return commentService.getNbCommentsToday();
	}

	@ApiOperation(value = "Returns the number of comments for the current week")
	@GetMapping("nb-this-week")
	public int getNbCommentsThisWeek() {
		return commentService.getNbCommentsThisWeek();
	}

	@ApiOperation(value = "Returns the number of comments with the label 'deleted'")
	@GetMapping("deleted/count")
	public int getNumberOfDeletedComments() {
		return commentService.getNbDeletedComments();
	}

	@ApiOperation(value = "Returns the number of comments with the label 'not deleted'")
	@GetMapping("not-deleted/count")
	public int getNumberOfNotDeletedComments() {
		return commentService.getNbNotDeletedComments();
	}

	@ApiOperation(value = "Returns the number of deleted comments per hour")
	@GetMapping("/deleted/stats/hours")
	public ResponseEntity<List<CommentHourStatistic>> getNbDeletedPerHour() {
		List<CommentHourStatistic> stats = commentService.getNbDeletedPerHour();
		return new ResponseEntity<List<CommentHourStatistic>>(stats, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Creates a list of comments and predicts the toxicity score")
	@PostMapping
	public ResponseEntity<List<Comment>> createComments(@RequestBody List<Comment> comments) {
		return new ResponseEntity<List<Comment>>(commentService.createComments(comments), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Updates an existing comment")
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Not Found")
			}
			)
	@PutMapping
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
		Comment oldComment = commentService.findById(comment.getId());
		if(oldComment == null) {
			return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
		}
		Comment updated = commentService.updateComment(comment);
		return new ResponseEntity<Comment>(updated, HttpStatus.OK);
	}
}
