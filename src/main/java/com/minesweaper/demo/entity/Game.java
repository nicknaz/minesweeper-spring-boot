package com.minesweaper.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Max(30)
    private Integer width;
    @Max(30)
    private Integer height;
    @Column(name = "mines_count")
    private Integer minesCount;
    private boolean completed;
    @NotBlank
    private String mines;
    @NotBlank
    private String opened;

}
