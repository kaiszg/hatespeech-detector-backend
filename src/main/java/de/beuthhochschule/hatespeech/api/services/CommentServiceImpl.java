/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 19.04.2018
 */
package de.beuthhochschule.hatespeech.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.beuthhochschule.hatespeech.api.model.CommentHourStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

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
		return Lists.newArrayList(commentRepo.findAll());
	}

	@Override
	public List<Comment> findAllByOrderScore() {
		return commentRepo.findAllByOrderByScoreAsc();
	}

	@Override
	public List<Comment> findLabelledCommentsOrderByScore() {
		return commentRepo.findByLabelNotNullOrderByScoreDesc();
	}

	@Override
	public List<Comment> findUnlabelledCommentsOrderByScore() {
		return commentRepo.findByLabelIsNullOrderByScoreDesc();
	}

	@Override
	public Comment findById(Long id) {
		Optional<Comment> comment = commentRepo.findById(id);
		if (comment.isPresent()) {
			return comment.get();
		}
		return null;
	}

	@Override
	public List<CommentHourStatistic> getNbDeletedPerHour() {
		List<CommentHourStatistic> stats = new ArrayList<CommentHourStatistic>();
		for(int i= 0; i <= 23; i++) {
			CommentHourStatistic commentStat = new CommentHourStatistic();
			commentStat.setHour(i);
			commentStat.setNbComments(commentRepo.getNbCommentsForHourAndLabel(i, "deleted"));
			stats.add(commentStat);
		}
		return stats;
	}

	@Override
	public Comment createComment(Comment comment) {
		return commentRepo.save(comment);
	}

	@Override
	public List<Comment> createComments(List<Comment> comments) {
		return Lists.newArrayList(commentRepo.saveAll(comments));
	}

	@Override
	public Comment updateComment(Comment comment) {
		return commentRepo.save(comment);
	}

	@Override
	public void deleteComment(Long id) {
		commentRepo.deleteById(id);
	}
}
