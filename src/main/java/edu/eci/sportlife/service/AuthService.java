package edu.eci.sportlife.service;

import edu.eci.sportlife.model.dto.AuthResponse;
import edu.eci.sportlife.model.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
}