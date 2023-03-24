package techcourse.fp.chess.domain;

import java.util.Map;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Piece;

public class ChessGame {

    private final Board board;
    private final ScoreCalculator scoreCalculator;

    public ChessGame(final Board board, final ScoreCalculator scoreCalculator) {
        this.board = board;
        this.scoreCalculator = scoreCalculator;
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
        return scoreCalculator.calculate(board.getBoard(), color);
    }
}
