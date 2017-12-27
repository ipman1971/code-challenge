package com.ipman1971.packlink.controllers;

import com.ipman1971.packlink.domain.Tracking;
import com.ipman1971.packlink.exceptions.PacklinkInternalOperationException;
import com.ipman1971.packlink.services.TrackingService;
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
@Api(value = "Tracking", description = "tracking a shipment")
public class TrackingController extends AbstractControllerHandler {

    @Autowired
    private TrackingService service;

    @RequestMapping(value = "/push", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Tracking a shipment",nickname = "pushShipment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @ResponseBody
    public String push(@RequestBody Tracking tracking, HttpServletRequest request,
                               HttpServletResponse response) throws PacklinkInternalOperationException {
        return service.push(tracking);
    }

}
