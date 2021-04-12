package chess.controller.web;

import chess.controller.web.dto.BoardDto;
import chess.controller.web.dto.ColorDto;
import chess.controller.web.dto.ScoreDto;
import chess.domain.game.BoardFactory;
import chess.domain.game.Command;
import chess.domain.game.Game;
import chess.domain.piece.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebChessController {
    Game game;

    public void init() {
        game = new Game(BoardFactory.create());
    }

    public void action(String command) {
        Command.from(command)
               .action(game);
    }

    public ColorDto currentPlayer() {
        return new ColorDto(game.currentPlayer());
    }

    public BoardDto board() {
        return new BoardDto(game.allBoard());
    }

    public boolean isFinished() {
        return game.isFinished();
    }

    public List<ScoreDto> score() {
        List<ScoreDto> scores = new ArrayList<>();
        Map<Color, Double> score = game.score();
        for (Color color : score.keySet()) {
            scores.add(new ScoreDto(color, score.get(color)));
        }
        return scores;
    }
}
