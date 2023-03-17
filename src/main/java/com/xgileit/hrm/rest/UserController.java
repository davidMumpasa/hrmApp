package com.xgileit.hrm.rest;

import com.xgileit.hrm.config.Constant;
import com.xgileit.hrm.dto.response.user.UserResponse;
import com.xgileit.hrm.dto.response.user.UserResponsePaginationDTO;
import com.xgileit.hrm.exception.NotFound;
import com.xgileit.hrm.persistance.entity.User;
import com.xgileit.hrm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public UserResponsePaginationDTO filterAndSortUsers(@RequestParam(defaultValue = "0")
                                                        List<Integer> roleId, @RequestParam(defaultValue = "0")
                                                        List<Integer> statusId, @RequestParam(defaultValue = "5")
                                                        int pageLength, @RequestParam(defaultValue = "0")
                                                        int pageNumber) throws NotFound {
        // filter the user's list and return a roleUserMappings array
        UserResponsePaginationDTO userResponsePaginationDTO = userService.filterUser(roleId,statusId,pageLength,pageNumber);

        return userResponsePaginationDTO;
    }

    @GetMapping("/search/{id}")
    public UserResponse getUser(@PathVariable int id) throws NotFound {
        UserResponse user = userService.getUser(id);

        return user;
    }
}
