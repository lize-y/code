package cn.X1ayu.rpc.server;

import cn.X1ayu.rpc.config.RpcServiceConfig;
import cn.X1ayu.rpc.transmission.RpcServer;
import cn.X1ayu.rpc.transmission.socket.server.SocketServer;

public class Main {
    public static void main(String[] args) {
        RpcServer server = new SocketServer();
        server.publishService(new RpcServiceConfig(new UserServiceimpl()));
        server.start();
    }
}
