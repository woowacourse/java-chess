package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.piece.Color;
import java.util.HashMap;
import java.util.Map;

public abstract class Ready implements State {

    protected final Board board;

    protected Ready(Board board) {
        this.board = board;
    }

    public static State start(Board board) {
        return new WhiteTurn(board);
    }

    public static State continueOf(Color color, Board board) {
        if (board.hasKing(color)) {
            return createTurn(color, board);
        }
        if (color == Color.BLACK) {
            return new WhiteWin(board);
        }
        return new BlackWin(board);
    }

    private static State createTurn(Color color, Board board) {
        if (color == Color.BLACK) {
            return new BlackTurn(board);
        }
        return new WhiteTurn(board);
    }

    @Override
    public final Board getBoard() {
        return new Board(board.getValue());
    }

    @Override
    public final Map<Color, Score> getScore() {
        double whiteScore = board.calculateScore(Color.WHITE);
        double blackScore = board.calculateScore(Color.BLACK);

        Map<Color, Score> scoreByColor = new HashMap<>();
        scoreByColor.put(Color.WHITE, new Score(whiteScore));
        scoreByColor.put(Color.BLACK, new Score(blackScore));

        return scoreByColor;
    }
}
