package cn.X1ayu.rpc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RpcResp<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reqId;
    private Integer code;
    private String message;
    private T data;

    public static <T> RpcResp<T> success(String reqId, T data) {
        return new RpcResp<T>(reqId, 0, "success", data);
    }

    public static <T> RpcResp<T> fail(String reqId, String message) {
        return new RpcResp<T>(reqId, -1, message, null);
    }
}
