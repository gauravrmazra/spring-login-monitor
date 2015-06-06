package in.javawithgaurav.cache;

import in.javawithgaurav.bean.SessionUser;

import java.util.LinkedList;
import java.util.List;

public class SessionUserCache {
    List<SessionUser> users = new LinkedList<SessionUser>();
    
    private static final SessionUserCache INSTANCE = new SessionUserCache();
    
    public SessionUserCache() {

    }

    public boolean remove(SessionUser user) {
        users.remove(user);
        return true;
    }
    
    public boolean add(SessionUser user) {
        users.add(user);
        return true;
    }
    
    public List<SessionUser> getAll() {
        return new LinkedList<SessionUser>(users);
    }
    
    public static SessionUserCache getInstance() {
        return INSTANCE;
    }
}
