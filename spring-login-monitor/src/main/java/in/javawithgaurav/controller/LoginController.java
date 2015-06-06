package in.javawithgaurav.controller;

import in.javawithgaurav.bean.SessionUser;
import in.javawithgaurav.bean.User;
import in.javawithgaurav.cache.SessionUserCache;
import in.javawithgaurav.service.LoginService;
import in.javawithgaurav.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private LoginService loginService;
    
    public LoginController() {
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse res) {
        String userName = request.getParameter("userName");
        char[] password = request.getParameter("password").toCharArray();
        
        if (loginService.login(userName, password)) {
            User user = userService.findByEmail(userName);
            SessionUser sessionUser = new SessionUser(user);
            request.getSession().setAttribute(SessionUser.SESSION_USER, sessionUser);
            ModelAndView mv = new ModelAndView("home");
            mv.addObject("user", user);
            return mv;
        }
        return new ModelAndView("login");
    }
    
    @RequestMapping(value="/loginUser")
    public String loginUsers(HttpServletRequest request, HttpServletResponse res) {
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER);
        if (sessionUser.hasAdminRole()) {
            return "loginUser";
        }
        return "home";
    }
    
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse res) {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionUser.SESSION_USER);
        session.invalidate();
        return "logoutsuccess";
    }

}
