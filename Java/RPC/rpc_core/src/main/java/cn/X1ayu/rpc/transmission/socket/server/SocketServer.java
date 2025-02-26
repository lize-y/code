package cn.X1ayu.rpc.transmission.socket.server;

import cn.X1ayu.rpc.dto.RpcReq;
import cn.X1ayu.rpc.dto.RpcResp;
import cn.X1ayu.rpc.transmission.RpcServer;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class SocketServer implements RpcServer {

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(8888)){
            log.info("socket server start");
            Socket socket;
            while ((socket= serverSocket.accept())!=null){
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                RpcReq rpcReq = (RpcReq) objectInputStream.readObject();
                log.info("socket server receive msg: {}", rpcReq);

                // 处理请求
                String data = "hello world";

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(RpcResp.success(rpcReq.getReqId(), data));
                objectOutputStream.flush();
            }
        }catch (Exception e){
            log.error("socket server error", e);
        }
    }
}
