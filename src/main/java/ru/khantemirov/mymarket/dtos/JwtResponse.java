package ru.khantemirov.mymarket.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowPrintStacktrace;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
