package indiecode.api.siguard.security.Controllers;


import indiecode.api.siguard.Persistence.Dto.LoginRequestDto;
import indiecode.api.siguard.security.Controllers.dto.AuthRespondeDto;
import indiecode.api.siguard.security.Jwt.JwtUtils;
import indiecode.api.siguard.security.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("auth/")
public class AuthController {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;



    @PostMapping("/login")
    public ResponseEntity<AuthRespondeDto> logInn(@RequestBody LoginRequestDto loginRequest) {

        try {
            AuthRespondeDto authRespondeDto = customUserDetailsService.login(loginRequest);
            return new ResponseEntity<>(authRespondeDto, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    //http://localhost:8080/swagger-ui/index.html

    @PostMapping("register")
    public String register(){
        return "Register successful";
    }
}
