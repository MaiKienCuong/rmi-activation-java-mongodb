package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String commentContent;
	private LocalDateTime date;

	@ManyToOne
	@JoinColumn(name = "newID")
	private News nw;

	public Comment() {
		super();
	}

	public Comment(String commentContent, LocalDateTime date, News nw) {
		super();
		this.commentContent = commentContent;
		this.date = date;
		this.nw = nw;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public News getNw() {
		return nw;
	}

	public void setNw(News nw) {
		this.nw = nw;
	}

	@Override
	public String toString() {
		return "Comment [commentContent=" + commentContent + ", date=" + date + ", nw=" + nw + "]";
	}

}
