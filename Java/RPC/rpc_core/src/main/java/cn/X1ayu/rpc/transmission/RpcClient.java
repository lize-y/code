package cn.X1ayu.rpc.transmission;

import cn.X1ayu.rpc.dto.RpcReq;
import cn.X1ayu.rpc.dto.RpcResp;

public interface RpcClient {
    RpcResp<?> sendRequest(RpcReq req);
}
