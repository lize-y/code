package cn.X1ayu.rpc.transmission;

import cn.X1ayu.rpc.config.RpcServiceConfig;

public interface RpcServer {
    void start();

    void publishService(RpcServiceConfig rpcServiceConfig);
}
