package cn.X1ayu.rpc.server;

import cn.X1ayu.rpc.transmission.RpcServer;
import cn.X1ayu.rpc.transmission.socket.server.SocketServer;

public class Main {
    public static void main(String[] args) {
        RpcServer server = new SocketServer();
        server.start();
    }
}
