package com.minesweaper.demo.controller;

import com.minesweaper.demo.dto.GameInfoResponse;
import com.minesweaper.demo.dto.GameTurnRequest;
import com.minesweaper.demo.dto.NewGameRequest;
import com.minesweaper.demo.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@CrossOrigin
@RestController
@AllArgsConstructor
@Slf4j
public class GameController {

    private final GameService gameService;

    @PostMapping("/new")
    public GameInfoResponse createNewGame(@RequestBody NewGameRequest newGameRequest) {
        GameInfoResponse response = gameService.createNewGame(newGameRequest);
        log.info("User create new game with id: " + response.getGameId());
        return response;
    }

    @PostMapping("/turn")
    public GameInfoResponse turnCell(@RequestBody GameTurnRequest gameTurnRequest) {
        log.info(gameTurnRequest.toString());
        GameInfoResponse response = gameService.turnCell(gameTurnRequest);
        //log.info("User create new game with id: " + response.getGameId());
        return response;
    }

}
