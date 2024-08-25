package org.example.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponseDTO {

    private Long id;
    private String name;
    private String lastName;
    private BigDecimal creditLimit;
    private Integer dueDate;
    private Boolean active;

}