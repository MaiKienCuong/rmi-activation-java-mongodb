package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String newsTitle;
	private String newsContent;
	private LocalDateTime creationDate;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> newsCategories;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> tags;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> cotes;

	public News() {
		super();
	}

	public News(Long id, String newsTitle, String newsContent, LocalDateTime creationDate, Set<String> newsCategories,
			Set<String> tags, Set<String> cotes) {
		super();
		this.id = id;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.creationDate = creationDate;
		this.newsCategories = newsCategories;
		this.tags = tags;
		this.cotes = cotes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Set<String> getNewsCategories() {
		return newsCategories;
	}

	public void setNewsCategories(Set<String> newsCategories) {
		this.newsCategories = newsCategories;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<String> getCotes() {
		return cotes;
	}

	public void setCotes(Set<String> cotes) {
		this.cotes = cotes;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", newsTitle=" + newsTitle + ", newsContent=" + newsContent + ", creationDate="
				+ creationDate + ", newsCategories=" + newsCategories + ", tags=" + tags + ", cotes=" + cotes + "]";
	}

}
