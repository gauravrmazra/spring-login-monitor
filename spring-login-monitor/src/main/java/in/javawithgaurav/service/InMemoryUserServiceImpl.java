package in.javawithgaurav.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import in.javawithgaurav.bean.Role;
import in.javawithgaurav.bean.User;

@Service
public class InMemoryUserServiceImpl implements UserService {
    
    
    public InMemoryUserServiceImpl() {
    }

    public User findByEmail(String email) {
        return LocalUserCache.userByEmail(email);
    }
    
    
    static final class LocalUserCache {
        private static final List<User> users;
        
        static {
            users = new ArrayList<User>();
            Role admin = InMemoryRoleCache.roleByName(InMemoryRoleCache.RoleHelper.ADMIN.getName());
            Role manager = InMemoryRoleCache.roleByName(InMemoryRoleCache.RoleHelper.MANAGER.getName());
            Role userRole = InMemoryRoleCache.roleByName(InMemoryRoleCache.RoleHelper.USER.getName());
            
            User user = new User();
            user.setEmail("user");
            user.setPassword("user");
            user.addRole(userRole);
            users.add(user);
            
            user = new User();
            user.setEmail("user1");
            user.setPassword("user1");
            user.addRole(userRole);
            users.add(user);
            
            user = new User();
            user.setEmail("user2");
            user.setPassword("user2");
            user.addRole(userRole);
            users.add(user);
            
            user = new User();
            user.setEmail("admin");
            user.setPassword("admin");
            user.addRole(admin);
            user.addRole(manager);
            users.add(user);
            
        }
        
        public static final User userByEmail(String email) {
            for (User user : users)
                if (user.getEmail().equals(email))
                    return user;
            
            return null;
        }
    }
    
    public static final class InMemoryRoleCache {
        private static final List<Role> roles;
        
        static {
            roles = new ArrayList<Role>();
            
            Role role = new Role();
            role.setName(RoleHelper.ADMIN.getName());
            
            roles.add(role);
            
            role = new Role();
            role.setName(RoleHelper.MANAGER.getName());
            
            roles.add(role);
            
            role = new Role();
            role.setName(RoleHelper.USER.getName());
            
            roles.add(role);
        }
        
        private enum RoleHelper {
            MANAGER("manager"), USER("user"), ADMIN("admin");
            
            private String name;
            RoleHelper(String name) {
                this.name = name;
            }
            
            public String getName() {
                return name;
            }
        }
        
        
        public static final Role roleByName(String name) {
            if (name == null || name.length() == 0) return null;
            
            for (Role role : roles)
                if (role.getName().equals(name))
                    return role;
                
            return null;
        }
        
        
        public static final boolean hasAdminRole(User user) {
            if (user == null || user.getRoles() == null) return false;
            
            final String admin = RoleHelper.ADMIN.getName();
            for (Role role : user.getRoles()) {
                if (role.getName().equals(admin)) return true;
            }
            
            return false;
        }
        
    }

}
