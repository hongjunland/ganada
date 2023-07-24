package example.ganada.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private int code;
    private String msg;
    private T results;
    public static BaseResponse ok(Object data){
        return BaseResponse.builder()
                .code(HttpStatus.OK.getCode())
                .results(data)
                .msg(HttpStatus.OK.getMessage())
                .build();
    }

    public static BaseResponse<?> badRequest() {
        return BaseResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getCode())
                .msg(HttpStatus.BAD_REQUEST.getMessage())
                .build();
    }

    public static BaseResponse<?> unauthorized() {
        return BaseResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.getCode())
                .msg(HttpStatus.UNAUTHORIZED.getMessage())
                .build();
    }

    public static BaseResponse<?> forbidden() {
        return BaseResponse.builder()
                .code(HttpStatus.FORBIDDEN.getCode())
                .msg(HttpStatus.FORBIDDEN.getMessage())
                .build();
    }

    public static BaseResponse<?> notFound() {
        return BaseResponse.builder()
                .code(HttpStatus.NOT_FOUND.getCode())
                .msg(HttpStatus.NOT_FOUND.getMessage())
                .build();
    }

    public static BaseResponse<?> internalServerError() {
        return BaseResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getCode())
                .msg(HttpStatus.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }

    public static BaseResponse<?> badGateway() {
        return BaseResponse.builder()
                .code(HttpStatus.BAD_GATEWAY.getCode())
                .msg(HttpStatus.BAD_GATEWAY.getMessage())
                .build();
    }

    public static BaseResponse<?> serviceUnavailable() {
        return BaseResponse.builder()
                .code(HttpStatus.SERVICE_UNAVAILABLE.getCode())
                .msg(HttpStatus.SERVICE_UNAVAILABLE.getMessage())
                .build();
    }

}
