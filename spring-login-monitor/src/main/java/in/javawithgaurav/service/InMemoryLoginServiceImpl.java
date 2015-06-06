package in.javawithgaurav.service;

import java.util.Arrays;

import in.javawithgaurav.bean.User;
import in.javawithgaurav.service.InMemoryUserServiceImpl.LocalUserCache;

public class InMemoryLoginServiceImpl implements LoginService {

    public InMemoryLoginServiceImpl() {
    }

    public boolean login(String email, char[] password) {
        User user = LocalUserCache.userByEmail(email);
        
        if (user == null) return false;

        return Arrays.equals(user.getPassword().toCharArray(), password);
    }
}
