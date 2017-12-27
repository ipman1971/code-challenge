package com.ipman1971.packlink.controllers;

import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.exceptions.PacklinkInternalOperationException;
import com.ipman1971.packlink.services.ShipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jcorredera on 24/12/17
 */
@RestController
@RequestMapping(value = "/api")
@Api(value = "RegisterShipment", description = "Register a shipment")
public class ShipmentController extends AbstractControllerHandler {

    @Autowired
    private ShipmentService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a shipment",nickname = "registerShipment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public String register(@RequestBody Shipment shipment, HttpServletRequest request,
                                   HttpServletResponse response) throws PacklinkInternalOperationException {
        return service.register(shipment);
    }

}
