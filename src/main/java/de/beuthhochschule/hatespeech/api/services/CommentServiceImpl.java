/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 19.04.2018
 */
package de.beuthhochschule.hatespeech.api.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import de.beuthhochschule.hatespeech.api.model.Comment;
import de.beuthhochschule.hatespeech.api.model.CommentHourStatistic;
import de.beuthhochschule.hatespeech.api.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * @author Kais
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
    public List<Comment> findLabelledComments(Optional<String> sortOrder) {
        if (sortOrder.isPresent()) {
            if (sortOrder.get().equals("date")) {
                return commentRepo.findByLabelNotNullOrderByTimestampDescScoreDesc();
            } else if (sortOrder.get().equals("article_id")) {
                return commentRepo.findByLabelNotNullOrderByArticleIdDescScoreDesc();
            } else {
                return null;
            }
        } else {
            return commentRepo.findByLabelNotNullOrderByScoreDesc();
        }

    }

    @Override
    public List<Comment> findUnlabelledComments(Optional<String> sortOrder) {
        if (sortOrder.isPresent()) {
            if (sortOrder.get().equals("date")) {
                return commentRepo.findByLabelIsNullOrderByTimestampDescScoreDesc();
            } else if (sortOrder.get().equals("article_id")) {
                return commentRepo.findByLabelIsNullOrderByArticleIdDescScoreDesc();
            } else {
                return null;
            }
        } else {
            return commentRepo.findByLabelIsNullOrderByScoreDesc();
        }
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
        for (int i = 0; i <= 23; i++) {
            CommentHourStatistic commentStat = new CommentHourStatistic();
            commentStat.setHour(i);
            commentStat.setNbComments(commentRepo.getNbCommentsForHourAndLabel(i, "deleted"));
            stats.add(commentStat);
        }
        return stats;
    }

    @Override
    public Comment createComment(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String input = "{\"comment\":\"" + comment.getText() + "\"}";
        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(input, headers);

        ResponseEntity<String> response = restTemplate
                .exchange("http://localhost:5000/predict", HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = null;
                root = mapper.readTree(response.getBody());
                JsonNode success = root.path("success");
                if(success.asBoolean()){
                    JsonNode prediction = root.path("prediction");
                    comment.setScore(Float.valueOf((float)prediction.asDouble()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(response);

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

    @Override
    public int getNbDeletedComments() {
        return commentRepo.countByLabel("deleted");
    }

    @Override
    public List<Comment> findTopCommentsOfCurrentWeek(int numberOfComments) {
        // get first day of the current week
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();
        LocalDate firstDayOfWeek = now.with(fieldISO, 1);
        Date firstDateOfWeek = Date.from(firstDayOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // get the date of tomorrow
        LocalDate tomorrow = now.plusDays(1);
        Date tomorrowDate = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return commentRepo.findUnlabelledBetweenDates(firstDateOfWeek, tomorrowDate, new PageRequest(0, numberOfComments));
    }

    @Override
    public int getNbNotDeletedComments() {
        return commentRepo.countByLabel("not deleted");
    }

    @Override
    public int getNbCommentsToday() {
        Date today = new Date();
        return commentRepo.countCommentsOfDate(today);
    }

    @Override
    public int getNbCommentsThisWeek() {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();
        LocalDate firstDayOfWeek = now.with(fieldISO, 1);
        Date firstDateOfWeek = Date.from(firstDayOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // get the date of tomorrow
        LocalDate tomorrow = now.plusDays(1);
        Date tomorrowDate = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return commentRepo.countCommentsBetweenDates(firstDateOfWeek, tomorrowDate);
    }
}
