package com.codegym.exam.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cities")
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name must be fill")
    private String name;

    @Positive
    @NotNull(message = "Area must be fill")
    private double area;

    @Positive
    @NotNull(message = "Popultion must be fill")
    private double population;

    @Positive
    @NotNull(message = "GDP must be fill")
    private double gdp;

    @Column
    @Size(max = 3000, min = 10)
    private String description;


    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
