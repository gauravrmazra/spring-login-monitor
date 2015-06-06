package in.javawithgaurav.bean;

import in.javawithgaurav.cache.SessionUserCache;
import in.javawithgaurav.service.InMemoryUserServiceImpl;

import java.util.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionUser implements HttpSessionBindingListener {
    public static final String SESSION_USER = "sessionUser";
    private User user;
    private Date loggedInDate;

    public SessionUser(User user) {
        this.user = user;
        loggedInDate = new Date(System.currentTimeMillis());
    }

    public void valueBound(HttpSessionBindingEvent event) {
        SessionUserCache.getInstance().add((SessionUser) event.getValue());
    }

    public void valueUnbound(HttpSessionBindingEvent event) {
        SessionUserCache.getInstance().remove((SessionUser) event.getValue());
    }

    public boolean hasAdminRole() {
        return InMemoryUserServiceImpl.InMemoryRoleCache.hasAdminRole(user);
    }

    public String getUserName() {
        return user.getEmail();
    }

    public String getLoggedInDateAsString() {
        return loggedInDate.toString();
    }

    @Override
    public String toString() {
        return "email: " + user.getEmail() + ", login date: " + loggedInDate.toString();
    }

}
