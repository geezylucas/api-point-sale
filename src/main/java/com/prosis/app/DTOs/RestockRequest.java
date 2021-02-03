package com.prosis.app.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestockRequest {
    private int userId;
    private int boxId;
    private int personId;
    private int productId;
    private double quantity;
    private String dateExpiry;
}
