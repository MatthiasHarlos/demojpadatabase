package com.newenergycodes.demojpadatabase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAttendance {
    //@JsonFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private String date;
    private long userId;


}
