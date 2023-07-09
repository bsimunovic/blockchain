package hr.ferit.blockchaindonations.service;

import hr.ferit.blockchaindonations.dto.AuthenticationResponse;
import hr.ferit.blockchaindonations.dto.UserDto;
import hr.ferit.blockchaindonations.model.User;
import hr.ferit.blockchaindonations.request.AuthenticationRequest;
import hr.ferit.blockchaindonations.request.RegistrationRequest;

import java.math.BigDecimal;

public interface UserService {
    AuthenticationResponse register(RegistrationRequest registrationRequest);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    User getUser(Long id);
    UserDto getUserByUsername(String username);
    BigDecimal addBalance(Long id, BigDecimal amount);
}
