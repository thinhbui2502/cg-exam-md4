package com.codegym.exam.formatter;

import com.codegym.exam.model.Country;
import com.codegym.exam.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class CountryFormatter implements Formatter<Country> {
    private CountryService countryService;

    @Autowired
    public CountryFormatter (CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Country parse(String text, Locale locale) throws ParseException {
        Optional<Country> optionalAccount = countryService.findById(Long.parseLong(text));
        return optionalAccount.get();
    }

    @Override
    public String print(Country object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }

}
