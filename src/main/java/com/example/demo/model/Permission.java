package com.example.demo.model;

import com.example.demo.model.enums.PermissionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate firstDay;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate untilDay;

    @ManyToOne
    @ToString.Exclude
    private Employee employee;

    private String employeeMail;

    @Enumerated(EnumType.STRING)
    private PermissionStatus permissionStatus = PermissionStatus.ONAY_BEKLENIYOR;
}
