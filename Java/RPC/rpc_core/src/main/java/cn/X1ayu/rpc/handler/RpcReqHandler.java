package cn.X1ayu.rpc.handler;

import cn.X1ayu.rpc.dto.RpcReq;
import cn.X1ayu.rpc.provider.ServiceProvider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class RpcReqHandler {
    private final ServiceProvider serviceProvider;

    public RpcReqHandler(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @SneakyThrows
    public Object invoke(RpcReq req) {
        String rpcServiceName = req.getRpcServiceName();
        Object service = serviceProvider.getService(rpcServiceName);

        log.debug("获取对应服务：{}",service.getClass().getCanonicalName());

        Method method = service.getClass().getMethod(req.getMethodName(), req.getParameterTypes());

        return method.invoke(service, req.getParameters());
    }
}
