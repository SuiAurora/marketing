package com.ksf.common.errorcode;

/**
 * 基础错误码定义
 */
public enum BaseErrorCode implements ErrorCode {

    // ========== 客户端错误 ==========
    CLIENT_ERROR("A000001", "客户端错误"),

    // ========== 系统端错误 ==========
    SERVICE_ERROR("B000001", "系统执行出错"),

    // ========== 远程服务错误 ==========
    REMOTE_ERROR("C000001", "调用第三方服务出错");

    private final String code;

    private final String message;

    BaseErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
