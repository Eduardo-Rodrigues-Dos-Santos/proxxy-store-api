package com.proxxy.store.api.v1.model.output;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {

    private List<T> content;
    private Integer pageNumber;
    private Integer totalPages;
    private Long totalElements;

    private PageResponse(Page<T> page) {

        this.content = page.getContent();
        this.pageNumber = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }

    public static <T> PageResponse<T> of(Page<T> page) {
        return new PageResponse<>(page);
    }
}
