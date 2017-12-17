package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "description",
    "author"
})
@JsonIgnoreProperties(ignoreUnknown = true)

public class Review {
	@JsonProperty("description")
	private String description;
	@JsonProperty("author")
	private String author;
	public Review() {
	}
	
    /**
     * 
     * @param desc
     * @param author
     */
	
	public Review(String desc,String author) {
		setDescription(desc);
		setAuthor(author);
	}
	
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}
	@JsonProperty("description")
	public void setDescription(String desc) {
		this.description=desc;
	}
	
	@JsonProperty("author")
	public String getAuthor() {
		return author;
	}
	@JsonProperty("author")
	public void setAuthor(String author) {
		this.author=author;
	}
	
	@Override
	public String toString() {
		return "Review [description=" + description + ", author=" + author + "]";
	}
}
