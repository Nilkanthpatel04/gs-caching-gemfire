package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This means even though other JSON attributes may be retrieved, they’ll be ignored., hence mark the class with @JsonIgnoreProperties.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
public class QuoteResponse {

  @JsonProperty("value")
  private Quote quote;

  @JsonProperty("type")
  private String status;

  public Quote getQuote() {
    return quote;
  }

  public String getStatus() {
    return status;
  }

  public void setQuote(Quote quote) {
    this.quote = quote;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "QuoteResponse{" +
        "quote=" + quote +
        ", status='" + status + '\'' +
        '}';
  }
}
