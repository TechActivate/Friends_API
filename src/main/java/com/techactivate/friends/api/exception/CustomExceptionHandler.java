package com.techactivate.friends.api.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomExceptionHandler extends AbstractHandlerExceptionResolver {
	    @Override
	    protected ModelAndView doResolveException(
	      HttpServletRequest request, 
	      HttpServletResponse response, 
	      Object handler, 
	      Exception ex) {
	        try {
	            if (ex instanceof IllegalArgumentException) {
	                return handleIllegalArgument((IllegalArgumentException) ex, request, response);
	            }
	        } catch (Exception handlerException) {
	            log.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
	        }
	        return null;
	    }

	private ModelAndView 
	      handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
	        response.sendError(HttpServletResponse.SC_CONFLICT);
	        String accept = request.getHeader(HttpHeaders.ACCEPT);
	        log.info(accept);
	        return new ModelAndView();
	    }

}
