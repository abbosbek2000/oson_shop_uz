package uz.spring.oson_shop_uz.admin.response;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private boolean success;
    private Object data;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
