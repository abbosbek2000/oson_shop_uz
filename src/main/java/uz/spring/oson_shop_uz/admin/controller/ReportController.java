package uz.spring.oson_shop_uz.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping
    public String getMessage() {
        return "how are you ?";
    }
}
