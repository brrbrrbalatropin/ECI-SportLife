package edu.eci.sportlife.service.impl;

import edu.eci.sportlife.exception.ResourceNotFoundException;
import edu.eci.sportlife.model.Role;
import edu.eci.sportlife.model.User;
import edu.eci.sportlife.model.dto.AuthResponse;
import edu.eci.sportlife.model.dto.RegisterRequest;
import edu.eci.sportlife.repository.UserRepository;
import edu.eci.sportlife.security.JwtService;
import edu.eci.sportlife.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceNotFoundException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, "Bearer", user.getEmail());
    }
}