package cn.X1ayu.rpc.transmission.socket.server;

import cn.X1ayu.rpc.config.RpcServiceConfig;
import cn.X1ayu.rpc.dto.RpcReq;
import cn.X1ayu.rpc.dto.RpcResp;
import cn.X1ayu.rpc.handler.RpcReqHandler;
import cn.X1ayu.rpc.provider.ServiceProvider;
import cn.X1ayu.rpc.provider.impl.SimpleServiceProvide;
import cn.X1ayu.rpc.transmission.RpcServer;
import cn.X1ayu.rpc.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@Slf4j
public class SocketServer implements RpcServer {
    private final int port;
    private final RpcReqHandler rpcReqHandler;
    private final ServiceProvider serviceProvider;
    private final ExecutorService executor;

    public SocketServer() {
        this(8888);
    }
    public SocketServer(int port) {
        this(port, new SimpleServiceProvide());
    }

    public SocketServer(int port, ServiceProvider serviceProvider) {
        this.port = port;
        this.serviceProvider = serviceProvider;
        this.rpcReqHandler = new RpcReqHandler(serviceProvider);
        this.executor = ThreadPoolUtils.createIoIntensiveThreadPool("socket-rpc-server-");
    }


    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            log.info("socket server start:{}",port);
            Socket socket;
            while ((socket= serverSocket.accept())!=null){
                executor.submit(new SocketReqHandle(socket, rpcReqHandler));
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
