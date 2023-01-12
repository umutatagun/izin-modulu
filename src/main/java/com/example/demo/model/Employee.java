package com.example.demo.model;

import com.example.demo.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String tckn;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    private Boolean isManager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "holiday_id", referencedColumnName = "id")
    private Holiday holiday;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Permission> permissions = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lg_id", referencedColumnName = "id")
    private Login login;
}
