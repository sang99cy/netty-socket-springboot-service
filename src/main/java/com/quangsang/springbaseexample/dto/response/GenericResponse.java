package com.quangsang.springbaseexample.dto.response;

import com.quangsang.springbaseexample.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse<T> extends BaseResponse {
    private T data;

    public GenericResponse() {
    }

    public GenericResponse(T data) {
        super(ErrorCode.OK);
        this.data = data;
    }

    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<>(data);
    }

    public static GenericResponse<String> success(String data) {
        return new GenericResponse<>(data);
    }
}
