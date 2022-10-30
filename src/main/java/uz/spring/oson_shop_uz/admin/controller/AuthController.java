package uz.spring.oson_shop_uz.admin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.spring.oson_shop_uz.admin.receive.LoginDTO;
import uz.spring.oson_shop_uz.admin.receive.RegisterDTO;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.admin.service.AuthService;
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDTO loginDto) {
        ApiResponse login = authService.login(loginDto);
        return ResponseEntity.status(login.isSuccess() ? 201 : 409).body(login);
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDTO registerDto) {
        ApiResponse register = authService.register(registerDto);
        return ResponseEntity.status(register.isSuccess() ? 201 : 409).body(register);
    }
}
