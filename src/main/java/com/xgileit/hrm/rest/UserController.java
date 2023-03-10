package com.xgileit.hrm.rest;

import com.xgileit.hrm.config.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Constant.Path.User.USER_CONTROLLER,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {


}
