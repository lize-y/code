package cn.X1ayu.rpc.server;


import cn.X1ayu.rpc.api.User;
import cn.X1ayu.rpc.api.UserService;

public class UserServiceimpl implements UserService {
    @Override
    public User getUser(Long id) {
        return new User(id,"X1ayu");
    }
}
