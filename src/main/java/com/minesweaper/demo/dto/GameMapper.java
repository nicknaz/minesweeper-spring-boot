package com.minesweaper.demo.dto;

import com.minesweaper.demo.entity.Game;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameMapper {

    public static int computeValue(int x, int y, Game game) {
        int res = 0;
        for (int deltaX = -1; deltaX <= 1; deltaX++) {
            for (int deltaY = -1; deltaY <= 1; deltaY++) {
                if (x + deltaX < 0 || y + deltaY < 0 || x + deltaX >= game.getWidth() || y + deltaY >= game.getHeight()) continue;
                char ch = game.getMines().charAt((deltaX + x) * game.getWidth() + (deltaY + y));
                res += ch == '2' ? 1 : ch - '0';
            }
        }
        return res;
    }

    public static GameInfoResponse toGameInfoResponse(Game game) {
        String[][] field = new String[game.getHeight()][game.getWidth()];
        System.out.println(game.getMines());
        System.out.println(game.getOpened());
        for (int i = 0; i < game.getHeight(); i++) {
            for (int j = 0; j < game.getHeight(); j++) {
                if (game.getOpened().charAt(i * game.getHeight() + j) == 't') {
                    if (game.getMines().charAt(i * game.getHeight() + j) == '1') {
                        field[i][j] = "M";
                    } else if (game.getMines().charAt(i * game.getHeight() + j) == '2') {
                        field[i][j] = "X";
                    } else {
                        field[i][j] = String.valueOf(computeValue(i, j, game));
                    }
                } else {
                    field[i][j] = " ";
                }

            }
        }

        return GameInfoResponse
                .builder()
                .gameId(String.valueOf(game.getId()))
                .height(game.getHeight())
                .width(game.getWidth())
                .minesCount(game.getMinesCount())
                .completed(game.isCompleted())
                .field(field)
                .build();
    }

}
