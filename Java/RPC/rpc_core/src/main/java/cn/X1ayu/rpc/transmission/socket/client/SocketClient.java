package cn.X1ayu.rpc.transmission.socket.client;

import cn.X1ayu.rpc.dto.RpcReq;
import cn.X1ayu.rpc.dto.RpcResp;
import cn.X1ayu.rpc.transmission.RpcClient;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class SocketClient implements RpcClient {
    @Override
    public RpcResp<?> sendRequest(RpcReq req) {
        try (Socket socket = new Socket("127.0.0.1", 8888)){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(req);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();

            return (RpcResp<?>) object;
        }catch (Exception e){
            log.error("socket client error", e);
        }

        return null;
    }
}
