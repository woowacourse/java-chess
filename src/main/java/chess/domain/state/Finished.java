package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.Result;
import chess.domain.board.Color;
import chess.domain.board.Piece;
import java.util.HashMap;
import java.util.Map;

abstract class Finished implements State {

    protected final Board board;

    Finished(Board board) {
        this.board = board;
    }

    @Override
    public State movePiece(Position src, Position dest) {
        throw new UnsupportedOperationException();
    }

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
    public final Map<Position, Piece> getBoard() {
        return board.getValue();
    }

    @Override
    public final Result getResult() {
        return board.calculateCurrentResult();
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
