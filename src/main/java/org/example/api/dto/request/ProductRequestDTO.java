package org.example.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductRequestDTO {

    private String title;
    private String description;
    private BigDecimal price;

}
