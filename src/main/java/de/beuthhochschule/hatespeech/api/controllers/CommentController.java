/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 25.04.2018
 */
package de.beuthhochschule.hatespeech.api.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.findAllByOrderScore();
        if(comments == null || comments.isEmpty()) {
            return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

	@GetMapping("/labelled")
	public ResponseEntity<List<Comment>> getLabelledComments() {
	    List<Comment> comments = commentService.findLabelledCommentsOrderByScore();
	    if(comments == null || comments.isEmpty()) {
            return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}
	
	@GetMapping("/unlabelled")
	public ResponseEntity<List<Comment>> getUnlabelledComments() {
        List<Comment> comments = commentService.findUnlabelledCommentsOrderByScore();
        if(comments == null || comments.isEmpty()) {
            return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment) /* throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException */ {
		// TODO import Keras-Model and predict score
		// MultiLayerNetwork network = KerasModelImport.importKerasSequentialModelAndWeights("D:\\Studium\\EDM\\test_model.h5");
		// System.out.println(network.conf().toJson());
        return new ResponseEntity<Comment>(commentService.createComment(comment), HttpStatus.CREATED);
	}
	
	@GetMapping("/init")
	public void init() {
		Comment first = new Comment();
		first.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
		first.setTimestamp(new Date());
		first.setUrl("http://www.heise.de/comments/145268");
		first.setScore(new Float(0.56));

		Comment second = new Comment();
		second.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.   \r\n" + 
				"\r\n" + 
				"Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.   \r\n" + 
				"\r\n" + 
				"Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse");
		second.setTimestamp(new Date());
		second.setUrl("http://www.heise.de/comments/568456367");
		second.setScore(new Float(0.7456));

		Comment third = new Comment();
		third.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
		third.setTimestamp(new Date());
		third.setUrl("http://www.heise.de/comments/56756845");
		third.setScore(new Float(0.234));
		third.setLabel("not deleted");

		Comment fourth = new Comment();
		fourth.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
		fourth.setTimestamp(new Date());
		fourth.setUrl("http://www.heise.de/comments/8956845763");
		fourth.setScore(new Float(0.987));
		fourth.setLabel("deleted");

		commentService.createComment(first);
		commentService.createComment(second);
		commentService.createComment(third);
		commentService.createComment(fourth);
	}
}
