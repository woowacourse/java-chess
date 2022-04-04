package chess.console.dto;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;

public class GameResult {
    private final Map<Position, Piece> board;
    private final Map<Color, Double> score;

    private GameResult(Map<Position, Piece> board, Map<Color, Double> score) {
        this.board = board;
        this.score = score;
    }

    public static GameResult ofBoard(Map<Position, Piece> board) {
        return new GameResult(board, new HashMap<>());
    }

    public static GameResult ofScore(Map<Color, Double> score) {
        return new GameResult(new HashMap<>(), score);
    }

    public boolean isBoard() {
        return !board.isEmpty() && score.isEmpty();
    }

    public boolean isScore() {
        return board.isEmpty() && !score.isEmpty();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Map<Color, Double> getScore() {
        return score;
    }
}
