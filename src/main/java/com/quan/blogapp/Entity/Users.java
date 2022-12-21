package com.quan.blogapp.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Table(name = "users")
@Entity(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @SequenceGenerator(
        name = "users_sequence",
        sequenceName = "users_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "users_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "username cannot be null")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "password cannot be null")
    @Column(name = "password", nullable = false)
    private String password;

    public Users(@NotBlank(message = "username cannot be null") String username,
            @NotBlank(message = "password cannot be null") String password) {
        this.username = username;
        this.password = password;
    }

    
}
