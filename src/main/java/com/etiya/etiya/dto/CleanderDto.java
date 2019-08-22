package com.etiya.etiya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CleanderDto {

    private Long id;
    private Float price;
    private AirplaneDto airplane;
    private Integer seatNumber;
    private Date flightTime;
    private AirportDto departure;
    private Date departureTime;
    private Date arrivalTime;
    private Set<TicketDto> ticket;
}
