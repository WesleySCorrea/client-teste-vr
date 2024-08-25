package org.example.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"pageable"})
public class PageConfig<T> extends PageImpl<T> {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PageConfig(@JsonProperty("content") List<T> content,
                      @JsonProperty("number") int page,
                      @JsonProperty("size") int size,
                      @JsonProperty("totalElements") long total) {
        super(content, PageRequest.of(page, size), total);
    }

    public PageConfig(Page<T> page) {
        super(page.getContent(), page.getPageable(), page.getTotalElements());
    }
}