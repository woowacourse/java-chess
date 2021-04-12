package chess.controller.web;

import chess.controller.ChessController;
import chess.controller.web.dto.BoardDto;
import chess.controller.web.dto.ColorDto;
import chess.controller.web.dto.ScoreDto;
import chess.domain.piece.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebChessController extends ChessController {

    public ColorDto currentPlayer() {
        return new ColorDto(game.currentPlayer());
    }

    public BoardDto board() {
        return new BoardDto(game.allBoard());
    }

    public boolean isFinished() {
        return game.isEnd();
    }

    public List<ScoreDto> score() {
        List<ScoreDto> scores = new ArrayList<>();
        Map<Color, Double> score = game.score();
        for (Color color : score.keySet()) {
            scores.add(new ScoreDto(color, score.get(color)));
        }
        return scores;
    }

    @Override
    public void status() {
        throw new UnsupportedOperationException("Web 에서는 허용되지 않는 커맨드입니다.");
    }
}
