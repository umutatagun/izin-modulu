package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdDate = LocalDate.now();

    @LastModifiedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedBy
    private String createdBy = "Admin";

}
