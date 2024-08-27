package org.example.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ShoppingItemsId {

    private Long orderId;
    private Long productId;
}
