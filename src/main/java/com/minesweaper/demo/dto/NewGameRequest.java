package com.minesweaper.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NewGameRequest {

    private Integer width;
    private Integer height;
    @JsonProperty("mines_count")
    private Integer minesCount;

}
