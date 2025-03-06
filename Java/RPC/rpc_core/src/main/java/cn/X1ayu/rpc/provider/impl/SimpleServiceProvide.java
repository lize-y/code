package cn.X1ayu.rpc.provider.impl;

import cn.X1ayu.rpc.config.RpcServiceConfig;
import cn.X1ayu.rpc.provider.ServiceProvider;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SimpleServiceProvide implements ServiceProvider {
    private final Map<String, Object> SERVICE_CACHE = new HashMap<>();

    @Override
    public void publishService(RpcServiceConfig rpcServiceConfig) {
        List<String> rpcServiceNames = rpcServiceConfig.rpcServiceNames();

        if (CollUtil.isEmpty(rpcServiceNames)){
            throw new RuntimeException("该服务没有实现接口");
        }

        log.debug("注册服务:{}", rpcServiceNames);

        rpcServiceNames.forEach(rpcServiceName -> {
            SERVICE_CACHE.put(rpcServiceName, rpcServiceConfig.getService());
        });
    }

    @Override
    public Object getService(String rpcServiceName) {
        if (!SERVICE_CACHE.containsKey(rpcServiceName)){
            throw new IllegalArgumentException("找不到对应服务:" + rpcServiceName);
        }
        return SERVICE_CACHE.get(rpcServiceName);
    }
}
