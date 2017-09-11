package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
public class Quote {
  private Long id;
  private String quote;

  public Long getId() {
    return id;
  }

  public String getQuote() {
    return quote;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setQuote(String quote) {
    this.quote = quote;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Quote quote1 = (Quote) o;

    if (id != null ? !id.equals(quote1.id) : quote1.id != null)
      return false;
    return quote != null ? quote.equals(quote1.quote) : quote1.quote == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (quote != null ? quote.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Quote{" +
        "id=" + id +
        ", quote='" + quote + '\'' +
        '}';
  }
}
