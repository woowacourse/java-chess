package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Result;
import chess.domain.piece.Color;

import java.util.HashMap;
import java.util.Map;

abstract class Running extends Ready {

    Running(Board board) {
        super(board);
    }

    public abstract State movePiece(Position src, Position dest);

    @Override
    public final Map<Color, Double> getScore() {
        double whiteScore = board.calculateScore(Color.WHITE);
        double blackScore = board.calculateScore(Color.BLACK);

        Map<Color, Double> scoreByColor = new HashMap<>();
        scoreByColor.put(Color.WHITE, whiteScore);
        scoreByColor.put(Color.BLACK, blackScore);

        return scoreByColor;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public final Result getResult() {
        return board.calculateCurrentWinner();
    }
}
