package chess.controller.web;

import chess.controller.web.dto.BoardDTO;
import chess.controller.web.dto.ColorDTO;
import chess.controller.web.dto.ScoreDTO;
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

    public ColorDTO currentPlayer() {
        return new ColorDTO(game.currentPlayer());
    }

    public BoardDTO board() {
        return new BoardDTO(game.allBoard());
    }

    public boolean isFinished() {
        return game.isFinished();
    }

    public List<ScoreDTO> score() {
        List<ScoreDTO> scores = new ArrayList<>();
        Map<Color, Double> score = game.score();
        for (Color color : score.keySet()) {
            scores.add(new ScoreDTO(color, score.get(color)));
        }
        return scores;
    }
}
