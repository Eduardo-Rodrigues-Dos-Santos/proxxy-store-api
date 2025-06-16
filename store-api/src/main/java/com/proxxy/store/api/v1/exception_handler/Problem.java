package com.proxxy.store.api.v1.exception_handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter

@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private OffsetDateTime timestamp;
    private List<InvalidField> invalidFields;
}