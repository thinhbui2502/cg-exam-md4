package com.codegym.exam.controller;

import com.codegym.exam.model.City;
import com.codegym.exam.model.Country;
import com.codegym.exam.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("country")
public class CountryController {
    @Autowired
    CountryService countryService;

    @GetMapping("/list")
    public ModelAndView showList(Pageable pageable) {
        Page<Country> countries;
        countries = countryService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("country/list");
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("country/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCity(@ModelAttribute Country country) {
        countryService.save(country);
        ModelAndView modelAndView = new ModelAndView("country/create");
        modelAndView.addObject("country", country);
        modelAndView.addObject("message", "New Country was created!");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Country> country = countryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("country/edit");
        modelAndView.addObject("country", country);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editCountry(@ModelAttribute Country country) {
        countryService.save(country);
        ModelAndView modelAndView = new ModelAndView("country/edit");
        modelAndView.addObject("country", country);
        modelAndView.addObject("message", "Country information has been successfully changed!");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteCity(@PathVariable Long id, Pageable pageable){
        countryService.remove(id);
        Page<Country> countries =countryService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("country/list");
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

}
