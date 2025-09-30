package haythem.payment.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        Integer id,
        @NotNull(message = "FirstName is required")
        String firstname,
        @NotNull(message = "Lastname is required")
        String lastname,
        @Email(message = "Customer Email is not correctly formatted")
        @NotNull(message = "Email is required")
        String email
) {
}
