package com.quangsang.springbaseexample.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class BaseDTO implements Serializable {
    private Long totalPage;
    private Long totalRow;
    private Long page;
    private Long pageSize;
    private String keyword;
    private List<Long> ids;
}
