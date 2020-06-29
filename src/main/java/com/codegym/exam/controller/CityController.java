package com.codegym.exam.controller;

import com.codegym.exam.model.City;
import com.codegym.exam.model.Country;
import com.codegym.exam.service.CityService;
import com.codegym.exam.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @ModelAttribute("countries")
    public Page<Country> countries(Pageable pageable) {
        return countryService.findAll(pageable);
    }

    @GetMapping("/list")
    public ModelAndView showList(Pageable pageable) {
        Page<City> cities;
        cities = cityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("city/list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCity(@Valid @ModelAttribute City city) {
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("city/create");
        modelAndView.addObject("city", city);
        modelAndView.addObject("message", "New City was created!");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("city/edit");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editCity(@ModelAttribute City city) {
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("city/edit");
        modelAndView.addObject("city", city);
        modelAndView.addObject("message", "City information has been successfully changed!");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteCity(@PathVariable Long id, Pageable pageable) {
        cityService.remove(id);
        Page<City> cities = cityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("city/list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("view/{id}")
    public ModelAndView viewCity(@PathVariable Long id) {
        City city = cityService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("city/detail");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

}
