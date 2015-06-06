package in.javawithgaurav.service;

import in.javawithgaurav.bean.User;

public interface UserService {
    public User findByEmail(String email);
}
