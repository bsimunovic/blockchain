package hr.ferit.blockchaindonations.controller;

import hr.ferit.blockchaindonations.dto.UserDto;
import hr.ferit.blockchaindonations.request.BalanceRequest;
import hr.ferit.blockchaindonations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

@RestController
@RequestMapping(path = "api/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) throws AccountNotFoundException {
        UserDto userDto;
        try{
            userDto = userService.getUserByUsername(username);
        }catch (IllegalStateException e){
            throw new AccountNotFoundException();
        }
        return ResponseEntity.ok(userDto);
    }
    @PostMapping("/addBalance")
    public ResponseEntity<BigDecimal> addBalance(@RequestBody BalanceRequest balanceRequest) {
        return ResponseEntity.ok(userService.addBalance(balanceRequest.getUserId(), balanceRequest.getAmount()));
    }
}
