package com.minesweaper.demo.service;

import com.minesweaper.demo.dto.GameInfoResponse;
import com.minesweaper.demo.dto.GameTurnRequest;
import com.minesweaper.demo.dto.NewGameRequest;
import com.minesweaper.demo.entity.Game;
import com.minesweaper.demo.exception.CellOpenedException;
import com.minesweaper.demo.exception.GameCompletedException;
import com.minesweaper.demo.exception.ManyMineException;
import com.minesweaper.demo.exception.NotFoundedException;
import com.minesweaper.demo.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

import static com.minesweaper.demo.dto.GameMapper.computeValue;
import static com.minesweaper.demo.dto.GameMapper.toGameInfoResponse;
import static java.lang.Math.random;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public GameInfoResponse createNewGame(NewGameRequest newGameRequest) {
        if (newGameRequest.getMinesCount() > newGameRequest.getHeight() * newGameRequest.getWidth() - 1) {
            throw new ManyMineException("Many mine!");
        }

        char[] falseArr = new char[newGameRequest.getHeight() * newGameRequest.getWidth()];
        Arrays.fill(falseArr, 'f');
        String opened = new String(falseArr);

        char[] minesArr = new char[newGameRequest.getHeight() * newGameRequest.getWidth()];
        Arrays.fill(minesArr, '0');
        int i = 0;
        while (i < newGameRequest.getMinesCount()) {
            int pos = (int) (random() * minesArr.length);
            if (minesArr[pos] == '1') continue;
            minesArr[pos] = '1';
            i++;
        }
        String mines = new String(minesArr);

        Game newGame = Game.builder()
                .width(newGameRequest.getWidth())
                .height(newGameRequest.getHeight())
                .minesCount(newGameRequest.getMinesCount())
                .completed(false)
                .mines(mines)
                .opened(opened)
                .build();

        gameRepository.save(newGame);


        return toGameInfoResponse(newGame);
    }

    @Override
    public GameInfoResponse turnCell(GameTurnRequest gameTurnRequest) {

        Game game = gameRepository.findById(Long.parseLong(gameTurnRequest.getGameId()))
                .orElseThrow(() -> new NotFoundedException("Game with this gameId got founded!"));

        if (game.isCompleted()) {
            throw new GameCompletedException("Game is completed!");
        }

        if (game.getOpened().charAt(gameTurnRequest.getRow() * game.getWidth() + gameTurnRequest.getCol()) == 't') {
            throw new CellOpenedException("Cell already opened!");
        }

        if (game.getMines().charAt(gameTurnRequest.getRow() * game.getWidth() + gameTurnRequest.getCol()) == '1') {
            StringBuilder newMines = new StringBuilder(game.getMines());
            newMines.setCharAt(gameTurnRequest.getRow() * game.getWidth() + gameTurnRequest.getCol(), '2');
            game.setMines(newMines.toString());
            openAllCell(game);
        } else {
            open(gameTurnRequest.getRow(), gameTurnRequest.getCol(), game);
            checkWin(game);
        }

        gameRepository.save(game);

        return toGameInfoResponse(game);
    }

    private void checkWin(Game game) {
        if (StringUtils.countOccurrencesOf(game.getOpened(), "f") == game.getMinesCount()) {
            openAllCell(game);
        }
    }

    private void openAllCell(Game game) {
        game.setCompleted(true);
        char[] falseArr = new char[game.getHeight() * game.getWidth()];
        Arrays.fill(falseArr, 't');
        String opened = new String(falseArr);
        game.setOpened(opened);
    }

    private void open(int x, int y, Game game){
        if(x < 0 || y < 0 || x >= game.getWidth() || y >= game.getHeight()) return;
        if(game.getOpened().charAt(x * game.getWidth() + y) == 't') return;
        StringBuilder newOpened = new StringBuilder(game.getOpened());
        newOpened.setCharAt(x * game.getWidth() + y, 't');
        game.setOpened(newOpened.toString());
        if(computeValue(x, y, game) != 0) return;
        open(x - 1, y, game);
        open(x + 1, y, game);
        open(x, y - 1, game);
        open(x, y + 1, game);
        open(x - 1, y - 1, game);
        open(x - 1, y + 1, game);
        open(x + 1, y - 1, game);
        open(x + 1, y + 1, game);
    }

}
