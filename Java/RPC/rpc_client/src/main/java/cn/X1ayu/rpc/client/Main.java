package cn.X1ayu.rpc.client;

import cn.X1ayu.rpc.api.User;
import cn.X1ayu.rpc.dto.RpcReq;
import cn.X1ayu.rpc.dto.RpcResp;
import cn.X1ayu.rpc.transmission.RpcClient;
import cn.X1ayu.rpc.transmission.socket.client.SocketClient;

public class Main {

    public static void main(String[] args) {

        String data = invoke(1L).toString();
        System.out.println(data);
    }

    private static <T> T invoke(Long id) {
        RpcClient client = new SocketClient();

        RpcReq req = RpcReq.builder()
                .reqId("123")
                .interfaceName("cn.X1ayu.rpc.server.UserService")
                .methodName("getUser")
                .parameters(new Object[]{id})
                .parameterTypes(new Class[]{Long.class})
                .build();

        RpcResp<?> rpcResp = client.sendRequest(req);
        return (T) rpcResp.getData();
    }
}
