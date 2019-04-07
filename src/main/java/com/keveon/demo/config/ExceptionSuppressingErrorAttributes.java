package com.keveon.demo.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * The type Exception suppressing error attributes.
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class ExceptionSuppressingErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.remove("exception");
        Object message = webRequest.getAttribute("javax.servlet.error.message", RequestAttributes.SCOPE_REQUEST);
        if (message != null) {
            errorAttributes.put("message", message);
        }
        return errorAttributes;
    }
}
