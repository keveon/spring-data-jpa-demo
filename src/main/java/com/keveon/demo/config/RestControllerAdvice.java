package com.keveon.demo.config;

import com.keveon.demo.commons.exceptions.ResourceCreatedFailedException;
import com.keveon.demo.commons.exceptions.ResourceDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Rest controller advice.
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @since 1.0.0
 */
@ControllerAdvice
public class RestControllerAdvice {
    /**
     * Handle illegal argument exception.
     *
     * @param ex       the ex
     * @param response the response
     * @throws IOException the io exception
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException ex,
                                               HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * Handle resource does not exist exception.
     *
     * @param ex       the ex
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    @ExceptionHandler(ResourceDoesNotExistException.class)
    public void handleResourceDoesNotExistException(ResourceDoesNotExistException ex,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(),
                "The resource '" + request.getRequestURI() + "' does not exist");
    }

    /**
     * Handle created failed exception.
     * TODO keveon on 2019/04/07. 只写资源创建失败太抽象了, 异常定义还需要具体一些.
     *
     * @param ex       the ex
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    @ExceptionHandler(ResourceCreatedFailedException.class)
    public void handleCreatedFailedException(ResourceDoesNotExistException ex,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
