package com.example.smartlock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long idUser;
    @NotNull(message = "Introdu numele")
    @Size(min = 3, max = 15,message = "Numele trebuie sa fie cuprins intre 3 si 15 caractere")
    private String firstName;

    @NotNull(message = "Introdu prenumele")
    @Size(min = 3, max = 15, message = "Prenumele trebuie sa fie cuprins intre 3 si 15 caractere")
    private String lastName;

    @NotNull(message = "Introdu adresa de mail")
    @Email
    @Pattern(regexp = "^[\\w.+\\-]+@gmail\\.com$")
    private String email;

    @NotNull(message = "Introdu parola")
    @Size(min=1, max = 100, message = "Parola trebuie sa contina minim 7 caractere")
    private String password;

    @NotNull(message = "Introdu data nasterii")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull( message = "Introdu adresa")
    @Size(max = 30)
    private String address;

    @NotNull
    @Pattern(regexp = "^(\\+4|)?(07\\d{2}|02\\d{2}|03\\d{2})(\\s|\\.|)?(\\d{3}(\\s|\\.|)){2}$",message = "Numarul  de telefon este invalid")
    @Size(max = 10, message = "The maximum length for mobile is 15 characters")
    private String mobile;

    private Boolean isActive;

    private String resetPasswordToken;

    private Long idRole;
}
