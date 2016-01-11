package hello;

import java.util.Collections;
import java.util.Map;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

/**
 * The QuoteService class chooses a randomly selected quote from Greg Turnquist's Quote Service
 * running in Pivotal CloudFoundry @ http://gturnquist-quoters.cfapps.io/api.
 *
 * Quote API web service endpoints include:
 *
 * /api to get all quotes
 * /api/random for a random quote
 * /api/{id} for specific quote
 *
 * @author John Blum
 * @see hello.Quote
 * @see hello.QuoteResponse
 * @see org.springframework.cache.annotation.Cacheable
 * @see org.springframework.cache.annotation.CachePut
 * @see org.springframework.web.client.RestTemplate
 * @see <a href="http://gturnquist-quoters.cfapps.io/api">Quote Service</a>
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class QuoteService {

	protected static final String ID_BASED_QUOTE_SERVICE_URL = "http://gturnquist-quoters.cfapps.io/api/{id}";
	protected static final String RANDOM_QUOTE_SERVICE_URL = "http://gturnquist-quoters.cfapps.io/api/random";

	private volatile boolean cacheMiss = false;

	private RestTemplate quoteServiceTemplate = new RestTemplate();

	/**
	 * Determines whether the previous service method invocation resulted in a cache miss.
	 *
	 * @return a boolean value indicating whether the previous service method invocation resulted in a cache miss.
	 */
	public boolean isCacheMiss() {
		boolean cacheMiss = this.cacheMiss;
		this.cacheMiss = false;
		return cacheMiss;
	}

	/**
	 * Triggers a cache miss.
	 */
	protected void setCacheMiss() {
		this.cacheMiss = true;
	}

	/**
	 * Requests a quote with the given identifier.
	 *
	 * @param id the identifier of the {@link Quote} to request.
	 * @return a {@link Quote} with the given ID.
	 */
	@Cacheable("Quotes")
	public Quote requestQuote(Long id) {
		setCacheMiss();
		return requestQuote(ID_BASED_QUOTE_SERVICE_URL, Collections.singletonMap("id", id));
	}

	/**
	 * Requests a completely random quote.
	 *
	 * @return a random {@link Quote}.
	 */
	@CachePut(cacheNames = "Quotes", key = "#result.id")
	public Quote requestRandomQuote() {
		setCacheMiss();
		return requestQuote(RANDOM_QUOTE_SERVICE_URL);
	}

	/* (non-Javadoc) */
	protected Quote requestQuote(String URL) {
		return requestQuote(URL, Collections.emptyMap());
	}

	/* (non-Javadoc) */
	protected Quote requestQuote(String URL, Map<String, Object> urlVariables) {
		QuoteResponse quoteResponse = quoteServiceTemplate.getForObject(URL, QuoteResponse.class, urlVariables);
		return quoteResponse.getQuote();
	}

}