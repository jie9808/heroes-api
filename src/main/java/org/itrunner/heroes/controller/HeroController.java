package org.itrunner.heroes.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.itrunner.heroes.dto.HeroDto;
import org.itrunner.heroes.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Hero Controller"})
@Slf4j
public class HeroController {
    private final HeroService service;

    @Autowired
    public HeroController(HeroService service) {
        this.service = service;
    }

    @ApiOperation("Get hero by id")
    @GetMapping("/{id}")
    public HeroDto getHeroById(@ApiParam(required = true, example = "1") @PathVariable("id") Long id) {
        return service.getHeroById(id);
    }

    @ApiOperation("Get all heroes")
    @GetMapping
    public Page<HeroDto> getHeroes(@ApiIgnore @SortDefault.SortDefaults({@SortDefault(sort = "name", direction = Sort.Direction.ASC)}) Pageable pageable) {
        return service.getAllHeroes(pageable);
    }

    @ApiOperation("Search heroes by name")
    @GetMapping("/")
    public List<HeroDto> searchHeroes(@ApiParam(required = true) @RequestParam("name") String name) {
        return service.findHeroesByName(name);
    }

    @ApiOperation("Add new hero")
    @PostMapping
    public HeroDto addHero(@ApiParam(required = true) @Valid @RequestBody HeroDto hero) {
        return service.saveHero(hero);
    }

    @ApiOperation("Update hero info")
    @PutMapping
    public HeroDto updateHero(@ApiParam(required = true) @Valid @RequestBody HeroDto hero) {
        return service.saveHero(hero);
    }

    @ApiOperation("Delete hero by id")
    @DeleteMapping("/{id}")
    public void deleteHero(@ApiParam(required = true, example = "1") @PathVariable("id") Long id) {
        service.deleteHero(id);
    }
}