package com.xgileit.hrm.rest;

import com.xgileit.hrm.config.Constant;
import com.xgileit.hrm.dto.response.user.UserResponse;
import com.xgileit.hrm.persistance.entity.RoleUserMapping;
import com.xgileit.hrm.persistance.entity.User;
import com.xgileit.hrm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Constant.Path.User.USER_CONTROLLER,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/filter")
    public List<UserResponse> filterAndSortUsers(@RequestParam String criteria) {
        // filter the user's list and return a roleUserMappings array
        List<RoleUserMapping> roleUserMappings = userService.findByList(criteria);

        //  get the user list and return it
        List<User> users = userService.sortByModifiedDate(roleUserMappings);

        // pass the roleUserMappings and the user list to be set to the response
        List<UserResponse> userResponses = userService.sortAndSetResponse(roleUserMappings,users);

        return userResponses;
    }

    @GetMapping("/search/{id}")
    public User getUser(@PathVariable int id){
        User user = userService.getUser(id);

        return user;
    }
}
