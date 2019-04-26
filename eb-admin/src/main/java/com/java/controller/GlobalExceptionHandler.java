package com.java.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * description：统一异常处理器
 * author：丁鹏
 * date：10:27
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handException(Exception ex) {
        return "redirect:/pages/error/exception.jsp";
    }

}
