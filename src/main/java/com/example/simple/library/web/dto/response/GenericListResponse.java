package com.example.simple.library.web.dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericListResponse<T, R> implements Serializable {

    public static final long serialVersionUID = 1L;

    private final List<R> dataList;

    public GenericListResponse(List<T> entityList, Function<T, R> mapper) {
        this.dataList = entityList.stream().map(mapper).collect(Collectors.toList());
    }

    public List<R> getDataList() {
        return dataList;
    }
}
