package uz.spring.oson_shop_uz.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.spring.oson_shop_uz.admin.entity.Role;
import uz.spring.oson_shop_uz.admin.entity.User;
import uz.spring.oson_shop_uz.admin.entity.enums.RoleName;
import uz.spring.oson_shop_uz.admin.receive.LoginDTO;
import uz.spring.oson_shop_uz.admin.receive.RegisterDTO;
import uz.spring.oson_shop_uz.admin.repository.UserRepository;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.admin.security.JwtProvider;

import java.util.Collections;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public ApiResponse login(LoginDTO loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()
            ));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getUsername());
                return new ApiResponse(token, true);
        } catch (BadCredentialsException e) {
            return new ApiResponse("Parol yoki login Hato", false);
        }
    }

    public ApiResponse register(RegisterDTO registerDto) {
        boolean existsByUsername = userRepository.existsByUsername(registerDto.getUsername());
        if (existsByUsername)
            return new ApiResponse("bunday username ", false);
        User user = new User(
                registerDto.getFirstName(),
                registerDto.getLastName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                Collections.singleton(new Role(RoleName.USER)),
                true

        );
        userRepository.save(user);
        return new ApiResponse("successfully registere", true);
    }
}
