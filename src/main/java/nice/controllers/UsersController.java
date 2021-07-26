package nice.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import nice.models.User;
import nice.services.UserService;

@Controller
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model, SecurityContextHolderAwareRequestWrapper request) {
        boolean isAdminSigned = request.isUserInRole("ROLE_ADMIN");

        Collection<User> users = userService.findAll();
        if (CollectionUtils.isEmpty(Collections.singleton(users))) {
            Response.noContent().build();
        }
        model.addAttribute("users", users);
        model.addAttribute("isAdminSigned", isAdminSigned);
        return "views/users";
    }

    @GetMapping(value = "/users/{id}")
    @ResponseBody
    public Response getUserByIdOrUserName(@PathVariable("id") String idOrUserName) {
        Optional<Object> userByIdOrName = Optional.ofNullable(userService.findByIdOrUserName(idOrUserName));
        if (userByIdOrName.isPresent()) {
            Response.noContent().build();
        }
        return Response.ok(userByIdOrName).build();
    }

    @GetMapping(value = "/createUser/{id}")
    public String createUser(Model model, @Valid User user) {
        if (StringUtils.isEmpty(user.getName())) {
            Response.noContent().build();
        }
        model.addAttribute("createUser", userService.createUser(user));
        return "views/success";
    }

    //

    @DeleteMapping(value = "/users/delete/{id}")
    public String deleteUser(Model model, User user) {
        userService.deleteUser(user.getId());
        return "views/success";
    }

    // @PutMapping(value = "users/delete/allUsers")
    // @ResponseBody
    // public Response deleteAllUsers() {
    // userService.deleteAllUsers();
    // return Response.ok().build();

    // }
}