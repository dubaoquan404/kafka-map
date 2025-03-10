package cn.typesafe.km.controller;

import cn.typesafe.km.controller.dto.LoginAccount;
import cn.typesafe.km.controller.dto.PasswordChange;
import cn.typesafe.km.entity.User;
import cn.typesafe.km.service.UserService;
import cn.typesafe.km.util.Web;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dushixiang
 * @date 2021/6/12 2:33 下午
 */
@RestController
public class AccountController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginAccount loginAccount) {
        String token = userService.login(loginAccount);
        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        return map;
//        return Map.of("token", token);
    }

    @PostMapping("/logout")
    public void logout() {
        String token = Web.getToken();
        userService.logout(token);
    }

    @GetMapping("/info")
    public User info() {
        String token = Web.getToken();
        return userService.info(token);
    }

    @PostMapping("/change-password")
    public void changePassword(@RequestBody PasswordChange passwordChange) {
        String token = Web.getToken();
        userService.changePassword(token, passwordChange);
        userService.logout(token);
    }
}
