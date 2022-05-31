package com.newenergycodes.demojpadatabase.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateAttendance {
    private Long id;
    private String date;
    private long userId;


}
