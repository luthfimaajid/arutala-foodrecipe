package arutala.backend.bookrecipe.util;

import arutala.backend.bookrecipe.dto.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ResponseHandler {
    public static ResponseEntity<Object> internalServerError(String message) {
        return ResponseEntity.internalServerError().body(constructBody(message, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    public static ResponseEntity<Object> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(constructBody(message, HttpStatus.NOT_FOUND));
    }

    public static ResponseEntity<Object> badRequset(String message) {
        return ResponseEntity.badRequest().body(constructBody(message, HttpStatus.BAD_REQUEST));
    }

    public static ResponseEntity<Object> badRequset(String message, List<String> errors) {
        return ResponseEntity.badRequest().body(constructBody(message, HttpStatus.BAD_REQUEST, errors));
    }

    public static ResponseEntity<Object> conflict(String message) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(constructBody(message, HttpStatus.CONFLICT));
    }

    public static ResponseEntity<Object> ok(String message) {
        return ResponseEntity.ok().body(constructBody(message, HttpStatus.OK));
    }

    public static ResponseEntity<Object> ok(String message, Object data) {
        return ResponseEntity.ok().body(constructBody(message, HttpStatus.OK, data));
    }

    public static ResponseEntity<Object> ok(String message, Integer total) {
        return ResponseEntity.ok().body(constructBody(message, HttpStatus.OK, total));
    }

    public static ResponseEntity<Object> created(String message) {
//        return ResponseEntity.created("LOCATION ???")
        return ResponseEntity.status(HttpStatus.CREATED).body(constructBody(message, HttpStatus.CREATED));
    }

    public static ResponseEntity<Object> send(String message, HttpStatus httpStatus, Object data) {
        return new ResponseEntity<>(constructBody(message, httpStatus, data), httpStatus);
    }

    public static ResponseEntity<Object> send(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(constructBody(message, httpStatus), httpStatus);
    }

    private static BaseResponse<Object> constructBody(String message, HttpStatus httpStatus) {
        return BaseResponse.builder()
                .message(message)
                .statusCode(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .build();
    }

    private static BaseResponse<Object> constructBody(String message, HttpStatus httpStatus, Object data) {
        return BaseResponse.builder()
                .message(message)
                .statusCode(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .data(data)
                .total(data instanceof List<?> ? ((List<?>) data).size() : null)
                .build();

    }

    private static BaseResponse<Object> constructBody(String message, HttpStatus httpStatus, List<String> errors) {
        return BaseResponse.builder()
                .message(message)
                .statusCode(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .errors(errors)
                .build();
    }

    private static BaseResponse<Object> constructBody(String message, HttpStatus httpStatus, Integer total) {
        return BaseResponse.builder()
                .message(message)
                .statusCode(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .total(total)
                .data(Collections.emptyList())
                .build();
    }
}
