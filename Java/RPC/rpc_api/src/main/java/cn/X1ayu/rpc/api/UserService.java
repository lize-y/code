package cn.X1ayu.rpc.api;

public interface UserService {
    User getUser(Long id);

    User getUserByUsername(String username);
}
