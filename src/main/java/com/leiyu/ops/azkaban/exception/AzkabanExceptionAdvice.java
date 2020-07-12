package com.leiyu.ops.azkaban.exception;

import com.leiyu.ops.common.entity.GeneralResult;
import com.leiyu.ops.common.exception.ExceptionAdvice;
import com.leiyu.ops.common.exception.WebException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Pitt
 * @date 2020-05-23
 */
@ControllerAdvice
public class AzkabanExceptionAdvice extends ExceptionAdvice {

    @ExceptionHandler(WebException.class)
    @ResponseBody // Note: If not add it, then page will return content as follows:
    //Whitelabel Error Page
    //This application has no explicit mapping for /error, so you are seeing this as a fallback.
    //
    //Fri May 01 14:26:03 CST 2020
    //There was an unexpected error (type=Not Found, status=404).
    //xxx
    @Override
    public GeneralResult handleCustomerException(final WebException ex, final HttpServletResponse response) {
        return super.handleCustomerException(ex, response);
    }

    /*
    @ExceptionHandler(Exception.class)
    @Override
    public JSONObject handleException(final Exception ex, final HttpServletResponse response) {
        return super.handleException(ex, response);
    }
    */

}
