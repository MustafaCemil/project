package com.etiya.etiya.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Ticket extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "customers_id")
    private Customers customers;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="calendar_id")
    private Calendar calendar;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "pnr")
    private String pnr;

    @Column(name = "price")
    private Float price;
}
