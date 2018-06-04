/*
 * Beuth University of Applied Sciences
 * Hatespeech Detector
 * Created on 19.04.2018
 */
package de.beuthhochschule.hatespeech.api.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author Kais
 *
 */
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name="text", length= 10000)
	private String text;
	
	@Column(name="score")
	private Float score;
	
	@Column(name="timestamp")
	private Date timestamp;
	
	@Column(name="label")
	private String label;

	@Column(name="sub_label")
	private String subLabel;
	
	@Column(name="url")
	private String url;

	@Column(name="article_id")
	private String articleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSubLabel() {
		return subLabel;
	}

	public void setSubLabel(String subLabel) {
		this.subLabel = subLabel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Comment comment = (Comment) o;
		return Objects.equals(id, comment.id) &&
				Objects.equals(text, comment.text) &&
				Objects.equals(score, comment.score) &&
				Objects.equals(timestamp, comment.timestamp) &&
				Objects.equals(label, comment.label) &&
				Objects.equals(subLabel, comment.subLabel) &&
				Objects.equals(url, comment.url) &&
				Objects.equals(articleId, comment.articleId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, text, score, timestamp, label, subLabel, url, articleId);
	}
}
