package techcourse.fp.chess.domain;

import java.util.Map;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Piece;

public class ChessGame {

    private final Board board;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public void move(final Position source, final Position target) {
        board.move(source, target);
    }

    public boolean isGameEnd() {
        return board.isGameEnd();
    }

    public Color findWinner() {
        return board.findWinner();
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public double findScoreByColor(Color color) {
        return ScoreCalculator.calculate(board.getBoard(), color);
    }

    public Color getTurn() {
        return board.getTurn();
    }
}
