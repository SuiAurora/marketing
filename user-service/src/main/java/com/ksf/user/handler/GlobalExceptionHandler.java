package com.ksf.user.handler;

import com.ksf.common.errorcode.BaseErrorCode;
import com.ksf.common.exception.AbstractException;
import com.ksf.common.web.result.Result;
import com.ksf.common.web.result.ResultBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截参数验证异常
     * <p>
     * 当请求的参数不满足验证条件时，会抛出MethodArgumentNotValidException异常
     * 本方法用于处理该类异常，提取出第一个字段错误信息作为异常说明，并记录错误日志
     *
     * @param request HTTP请求对象，用于获取请求方法和URL
     * @param ex      方法参数校验不通过时抛出的异常
     * @return 表示操作失败的Result对象，包含客户端错误信息和错误码
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Void> validExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError firstFieldError = bindingResult.getFieldErrors().get(0);
        String exceptionStr = Optional
                .ofNullable(firstFieldError)
                .map(FieldError::getDefaultMessage)
                .orElse("");
        log.error("[{}] {} [ex] {}", request.getMethod(), getUrl(request), exceptionStr);
        return ResultBuilder.failure(BaseErrorCode.CLIENT_ERROR.code(), exceptionStr);
    }


    /**
     * 拦截应用内抛出的异常
     * <p>
     * 本方法主要用于捕获和处理由应用程序抛出的自定义异常，这些异常继承自AbstractException
     * 当异常的原始原因（cause）存在时，会记录详细的错误信息，否则只记录基本的异常信息
     *
     * @param request 当前的HTTP请求对象，用于日志记录
     * @param ex      自定义异常对象
     * @return 表示操作失败的Result对象，通常用于向用户返回错误提示信息
     */
    @ExceptionHandler(value = {AbstractException.class})
    public Result<Void> abstractException(HttpServletRequest request, AbstractException ex) {
        if (ex.getCause() != null) {
            log.error("[{}] {} [ex] {}", request.getMethod(), request.getRequestURL().toString(), ex, ex.getCause());
        } else {
            log.error("[{}] {} [ex] {}", request.getMethod(), request.getRequestURL().toString(), ex.toString());
        }
        return ResultBuilder.failure(ex);
    }


    /**
     * 拦截未捕获异常
     * 此方法用于处理所有未被捕获的异常，包括Error和Exception。
     * 它通过记录异常日志并返回一个表示失败的结果对象来处理这些异常。
     *
     * @param request   HTTP请求对象，用于获取请求方法和URL
     * @param throwable 抛出的异常对象
     * @return 表示操作失败的Result对象，其中包含通用的错误信息
     */
    @ExceptionHandler(value = Throwable.class)
    public Result<Void> defaultErrorHandler(HttpServletRequest request, Throwable throwable) {
        log.error("[{}] {} ", request.getMethod(), getUrl(request), throwable);
        return ResultBuilder.failure();
    }


    /**
     * 获取请求url
     * 该方法用于拼接完整的请求URL，包括基础URL和查询字符串
     *
     * @param request HTTP请求对象
     * @return 完整的请求URL
     */
    private String getUrl(HttpServletRequest request) {
        if (request.getQueryString() == null || request.getQueryString().isEmpty()) {
            return request.getRequestURL().toString();
        } else {
            return request.getRequestURL().toString() + "?" + request.getQueryString();
        }
    }

}
