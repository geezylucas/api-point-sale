package com.prosis.app.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int id;
    private String image;
    private String barcode;
    private String name;
    private String description;
    private int inventoryMin;
    private BigDecimal priceIn;
    private BigDecimal priceOut1;
    private BigDecimal priceOut2;
    private BigDecimal priceOut3;
    private Double inventoryOut3;
    private String unit;
    private String presentation;
    @JsonProperty
    private boolean status;
    @JsonProperty
    private boolean isBulk;
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private String createdAt;
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private String updatedAt;
    private Integer userId;
    private Integer categoryId;
    private String userUsername;
    private String categoryName;

    public Date getCreatedAtConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.createdAt);
    }

    public void setCreatedAtConverted(Timestamp date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.createdAt = dateFormat.format(date);
    }

    public Date getUpdatedAtConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.updatedAt);
    }

    public void setUpdatedAtConverted(Timestamp date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.updatedAt = dateFormat.format(date);
    }
}
