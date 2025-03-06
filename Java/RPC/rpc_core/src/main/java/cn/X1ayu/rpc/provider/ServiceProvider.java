package cn.X1ayu.rpc.provider;

import cn.X1ayu.rpc.config.RpcServiceConfig;

public interface ServiceProvider {
    void publishService(RpcServiceConfig rpcServiceConfig);

    Object getService(String rpcServiceName);
}
