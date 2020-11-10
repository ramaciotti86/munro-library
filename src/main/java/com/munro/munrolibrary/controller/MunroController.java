package com.munro.munrolibrary.controller;

import com.munro.munrolibrary.model.Munro;
import com.munro.munrolibrary.service.MunroService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    //localhost:8080/munros?category=MUN&min=10&max=45&height=DESC&name=DESC&limit=30

    //@RequestMapping(method = RequestMethod.GET, path = "/munros", name = "getMunroByFilter", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(method = RequestMethod.GET, path = "/munros", name = "getMunroByFilter", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Munro> getMunroByFilter(@RequestParam(value = "category", required = false) String[] category,
                                        @RequestParam(value = "min", required = false) Double min,
                                        @RequestParam(value = "max", required = false) Double max,
                                        @RequestParam(value = "height", required = false) String height, //ASC or DESC
                                        @RequestParam(value = "name", required = false) String name, //ASC or DESC
                                        @RequestParam(value = "limit", required = false) Integer limit){
        return munroService.getMunroListByFilter(category, min, max, height, name, limit);
    }
}
