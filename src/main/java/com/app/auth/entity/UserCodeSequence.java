package com.app.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_code_sequence")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCodeSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;
    private int month;
    private int count;
}
