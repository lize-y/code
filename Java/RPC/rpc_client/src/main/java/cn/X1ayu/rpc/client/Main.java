package cn.X1ayu.rpc.client;

import cn.X1ayu.rpc.api.User;
import cn.X1ayu.rpc.api.UserService;
import cn.X1ayu.rpc.dto.RpcReq;
import cn.X1ayu.rpc.dto.RpcResp;
import cn.X1ayu.rpc.proxy.RpcClientProxy;
import cn.X1ayu.rpc.transmission.RpcClient;
import cn.X1ayu.rpc.transmission.socket.client.SocketClient;

public class Main {

    public static void main(String[] args) {
        UserService userService = getProxy(UserService.class);
        User user = userService.getUser(114L);
        System.out.println(user);
    }

    private static <T> T getProxy(Class<T> clazz) {
        RpcClient client = new SocketClient("127.0.0.1", 8888);
        RpcClientProxy proxy = new RpcClientProxy(client);
        return proxy.getProxy(clazz);
    }
}
