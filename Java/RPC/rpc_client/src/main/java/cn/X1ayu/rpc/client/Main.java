package cn.X1ayu.rpc.client;

import cn.X1ayu.rpc.api.User;
import cn.X1ayu.rpc.dto.RpcReq;
import cn.X1ayu.rpc.dto.RpcResp;
import cn.X1ayu.rpc.transmission.RpcClient;
import cn.X1ayu.rpc.transmission.socket.client.SocketClient;

public class Main {

    public static void main(String[] args) {

        String data = invoke("X1").toString();
        System.out.println(data);
    }

    private static <T> T invoke(String name) {
        RpcClient client = new SocketClient();

        RpcReq req = RpcReq.builder()
                .reqId("5")
                .interfaceName("cn.X1ayu.rpc.api.UserService")
                .methodName("getUserByUsername")
                .parameters(new Object[]{name})
                .parameterTypes(new Class[]{String.class})
                .build();

        RpcResp<?> rpcResp = client.sendRequest(req);
        return (T) rpcResp;
    }
}
