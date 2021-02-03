package com.prosis.app.DTOs;

import lombok.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int id;
    private String image;
    private String name;
    private String description;
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private String createdAt;

    public Date getCreatedAtConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.createdAt);
    }

    public void setCreatedAtConverted(Timestamp date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.createdAt = dateFormat.format(date);
    }
}
