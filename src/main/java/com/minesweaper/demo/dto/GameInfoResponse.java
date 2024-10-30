package com.minesweaper.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameInfoResponse {
    @JsonProperty("game_id")
    private String gameId;
    private Integer width;
    private Integer height;
    private Integer minesCount;
    private boolean completed;
    private String[][] field;

}
