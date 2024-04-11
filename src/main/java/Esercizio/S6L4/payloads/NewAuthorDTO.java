package Esercizio.S6L4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewAuthorDTO(
        @NotEmpty(message= "il nome è obbligatorio")
        @Size(min = 5, max =40, message = "il nome deve essere compreso tra i 5 e i 40 caratteri")
        String name,
        @NotEmpty(message= "il conome è obbligatorio")
        @Size(min = 5, max =40, message = "il nome deve essere compreso tra i 5 e i 40 caratteri")
        String surname,
        @NotEmpty(message= "l email è obbligatoria")
        @Email(message = "l email non è valida")
        String email,
        @NotEmpty(message= "data di nascita è obbligatoria")
        String dateOfBirth,
        @NotEmpty(message= "avatar obbligatorio")
        String avatar) {
}
