package org.example.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class ClientRequestDTO {

    private Long id;
    private String name;
    private String lastName;
    private BigDecimal creditLimit;
    private Integer dueDate;

}
