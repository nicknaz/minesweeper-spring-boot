package com.minesweaper.demo.service;

import com.minesweaper.demo.dto.GameInfoResponse;
import com.minesweaper.demo.dto.GameTurnRequest;
import com.minesweaper.demo.dto.NewGameRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface GameService {

    GameInfoResponse createNewGame(NewGameRequest newGameRequest);
    GameInfoResponse turnCell(GameTurnRequest gameTurnRequest);

}
