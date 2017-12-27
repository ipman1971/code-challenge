package com.ipman1971.packlink.controllers;

import com.ipman1971.packlink.domain.ErrorInfo;
import com.ipman1971.packlink.exceptions.DataFormatException;
import com.ipman1971.packlink.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;


/**
 * Created by jcorredera on 25/12/17 - 14:10.
 * <p>
 * Debe ser extendida por todos los controllers, añade mapeo de excepciones y cualquier
 * funcionalidad comun a todos los controllers
 * <p>
 */
public abstract class AbstractControllerHandler {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    protected ApplicationEventPublisher eventPublisher;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public @ResponseBody
    ErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        LOG.info("Problemas al convertir los datos de la request : " + ex.getMessage());
        return new ErrorInfo(ex, "Problemas al convertir los datos de la request");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public @ResponseBody
    ErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        LOG.info("Recurso no encontrado:" + ex.getMessage());
        return new ErrorInfo(ex, "Recurso no encontrado");
    }

}
