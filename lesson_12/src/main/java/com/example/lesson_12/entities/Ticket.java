package com.example.lesson_12.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.sql.Timestamp;


@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "ticket_type")
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private TicketType type;

    @CreationTimestamp
    @Column(name = "creation_date")
    private Timestamp creationDate;
}
