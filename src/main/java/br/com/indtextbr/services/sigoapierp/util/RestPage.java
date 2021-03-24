package br.com.indtextbr.services.sigoapierp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;



public class RestPage<T> extends PageImpl<T> {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestPage(@JsonProperty("content") List<T> content,
                        @JsonProperty("number") int page,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") long total) {
        super(content, PageRequest.of(page, size), total);
    }
	public RestPage(List<T> content, Pageable pageable, long total) {
	    super(content, pageable, total);
	    // TODO Auto-generated constructor stub
	  }

	  public RestPage(List<T> content) {
	    super(content);
	    // TODO Auto-generated constructor stub
	  }

	  /* PageImpl does not have an empty constructor and this was causing an issue for RestTemplate to cast the Rest API response
	   * back to Page.
	   */
	  public RestPage() {
	    super(new ArrayList<T>());
	  }
}