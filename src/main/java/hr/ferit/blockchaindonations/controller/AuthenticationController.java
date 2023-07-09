package hr.ferit.blockchaindonations.controller;

import hr.ferit.blockchaindonations.dto.AuthenticationResponse;
import hr.ferit.blockchaindonations.request.AuthenticationRequest;
import hr.ferit.blockchaindonations.request.RegistrationRequest;
import hr.ferit.blockchaindonations.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/auth")
@CrossOrigin
@AllArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
