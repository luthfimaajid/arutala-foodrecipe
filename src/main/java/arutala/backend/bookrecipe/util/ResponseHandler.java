package arutala.backend.bookrecipe.util;

import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

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

    public static ResponseEntity<Object> ok(String message) {
        return ResponseEntity.ok().body(constructBody(message, HttpStatus.OK));
    }

    public static ResponseEntity<Object> ok(String message, Object data) {
        return ResponseEntity.ok().body(constructBody(message, HttpStatus.OK, data));
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

    private static Map<String, Object> constructBody(String message, HttpStatus httpStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("statusCode", httpStatus.value());
        map.put("status", httpStatus.getReasonPhrase());
        return map;
    }

    private static Map<String, Object> constructBody(String message, HttpStatus httpStatus, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", httpStatus.value());
        map.put("status", httpStatus.getReasonPhrase());
        map.put("message", message);
        map.put("data", data);
        return map;
    }
}
