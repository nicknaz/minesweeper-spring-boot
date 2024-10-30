package com.minesweaper.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GameTurnRequest {
    @JsonProperty("game_id")
    private String gameId;
    private Integer col;
    private Integer row;
}
