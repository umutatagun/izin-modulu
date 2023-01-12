package com.example.demo.model;

import com.example.demo.model.enums.Role;
import lombok.*;

import javax.persistence.*;

@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Builder
public class Login extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.EMPLOYEE;
}
