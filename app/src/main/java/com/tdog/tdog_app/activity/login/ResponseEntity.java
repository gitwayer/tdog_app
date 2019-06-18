package com.tdog.tdog_app.activity.login;

/**
 * 返回实体
 */
public class ResponseEntity {
    private String success;
    private String code;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "success='" + success + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
