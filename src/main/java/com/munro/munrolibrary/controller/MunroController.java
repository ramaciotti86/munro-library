package com.munro.munrolibrary.controller;

import com.munro.munrolibrary.model.Munro;
import com.munro.munrolibrary.service.MunroService;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@Validated
@RestController
public class MunroController {

    private final MunroService munroService;

    public MunroController(MunroService munroService) {
        this.munroService = munroService;
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/munros", name = "getMunroByFilter", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(method = RequestMethod.GET, path = "/munros", name = "getMunroByFilter", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Munro> getMunroByFilter(@PathParam(value = "category") String category){
        return munroService.getMunroListByFilter(category);
    }
}
