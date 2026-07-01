package org.example.usersystemjwt.controller;

import org.example.usersystemjwt.dto.LoginRequest;
import org.example.usersystemjwt.dto.LoginResponse;
import org.example.usersystemjwt.dto.SignupRequest;
import org.example.usersystemjwt.dto.UpdateProfileRequest;
import org.example.usersystemjwt.entity.User;
import org.example.usersystemjwt.security.JwtUtil;
import org.example.usersystemjwt.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService service;
    private final JwtUtil jwtUtil;

    public UserController(
            UserService service,
            JwtUtil jwtUtil) {

        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public User signup(
            @RequestBody SignupRequest request) {

        return service.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        User user = service.login(request);

        String token =
                jwtUtil.generateToken(
                        user.getUsername());

        return new LoginResponse(token);
    }

    @GetMapping("/profile")
    public User profile(
            @RequestHeader("Authorization")
            String authHeader) {

        String token =
                authHeader.substring(7);

        String username =
                jwtUtil.extractUsername(token);

        return service.findByUsername(username);
    }

    @PutMapping("/profile")
    public User updateProfile(
            @RequestHeader("Authorization")
            String authHeader,
            @RequestBody UpdateProfileRequest request) {

        String token =
                authHeader.substring(7);

        String username =
                jwtUtil.extractUsername(token);

        User user =
                service.findByUsername(username);

        return service.updateProfile(
                user.getId(),
                request);
    }

    @DeleteMapping("/profile")
    public String deleteProfile(
            @RequestHeader("Authorization")
            String authHeader) {

        String token =
                authHeader.substring(7);

        String username =
                jwtUtil.extractUsername(token);

        User user =
                service.findByUsername(username);

        service.deleteProfile(user.getId());

        return "Profile Deleted Successfully";
    }


}
