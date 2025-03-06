package cn.X1ayu.rpc.transmission.socket.server;

import cn.X1ayu.rpc.config.RpcServiceConfig;
import cn.X1ayu.rpc.dto.RpcReq;
import cn.X1ayu.rpc.dto.RpcResp;
import cn.X1ayu.rpc.handler.RpcReqHandler;
import cn.X1ayu.rpc.provider.ServiceProvider;
import cn.X1ayu.rpc.provider.impl.SimpleServiceProvide;
import cn.X1ayu.rpc.transmission.RpcServer;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class SocketServer implements RpcServer {
    ServiceProvider serviceProvider = new SimpleServiceProvide();

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(8888)){
            log.info("socket server start");
            Socket socket;
            while ((socket= serverSocket.accept())!=null){
                new Thread(new SocketReqHandle(socket, new RpcReqHandler(serviceProvider))).start();
            }
        }catch (Exception e){
            log.error("socket server error", e);
        }
    }

    @Override
    public void publishService(RpcServiceConfig rpcServiceConfig) {
        serviceProvider.publishService(rpcServiceConfig);
    }


}
