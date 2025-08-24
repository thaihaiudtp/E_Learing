package com.demo.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int status;          // HTTP status code
    private String message;      // Mô tả ngắn
    private T data;              // Payload chính
    private Object meta;         // Metadata (phân trang, filter...)
    private Object summary;      // Thống kê nhanh
    private Object errors;

    public static <T> ApiResponse<T> success(int status, String message, T data, Object meta, Object summary) {
        return new ApiResponse<>(status, message, data, meta, summary, null);
    }

    public static <T> ApiResponse<T> error(int status, String message, Object errors) {
        return new ApiResponse<>(status, message, null, null, null, errors);
    }
}
