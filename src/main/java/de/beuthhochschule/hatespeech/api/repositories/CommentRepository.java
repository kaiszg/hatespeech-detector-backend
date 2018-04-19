/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 19.04.2018
 */
package de.beuthhochschule.hatespeech.api.repositories;

import org.springframework.data.repository.CrudRepository;

import de.beuthhochschule.hatespeech.api.model.Comment;

/**
 *
 * @author Kais
 *
 */
public interface CommentRepository extends CrudRepository<Comment, Long>{

}
