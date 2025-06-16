package org.example.kushac.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationRequest {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate reservationDate;
    public LocalTime reservationTime;
    public Integer guestsCount;
    public String contactPhone;
}

