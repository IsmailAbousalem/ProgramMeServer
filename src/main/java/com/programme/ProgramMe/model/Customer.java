package com.programme.ProgramMe.model;

import com.programme.ProgramMe.repository.OnCreate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(groups = OnCreate.class, message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(groups = OnCreate.class, message = "Number cannot be null")
    @Pattern(regexp = "\\d{10}", message = "Number must be a valid 10-digit phone number")
    private String number;

    @NotNull(groups = OnCreate.class, message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(groups = OnCreate.class, message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$",
            message = "Password must have at least 6 characters and contain at least one number, one lowercase and one uppercase letter, and one special character.")
    private String password;
}
