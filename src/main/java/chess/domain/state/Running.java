package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Result;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

abstract class Running implements State {

    protected final Board board;

    Running(Board board) {
        this.board = board;
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
    public Map<Position, Piece> getBoard() {
        return board.getValue();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public final Result getResult() {
        return board.calculateCurrentResult();
    }
}
