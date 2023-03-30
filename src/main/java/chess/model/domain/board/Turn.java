package chess.model.domain.board;

import chess.model.domain.piece.Color;
import chess.model.domain.piece.Piece;
import chess.model.exception.IsNotPieceTurnException;

public class Turn {

    public static final String IS_NOT_PIECE_TURN_MESSAGE = "해당 말의 턴이 아닙니다.";
    public static final Color INITIAL_TURN = Color.WHITE;

    private Color turn;

    public Turn(final Color turn) {
        this.turn = turn;
    }

    public Turn() {
        this(INITIAL_TURN);
    }

    public void changeTurn() {
        if (turn == Color.WHITE) {
            turn = Color.BLACK;
            return;
        }
        turn = Color.WHITE;
    }

    public void validateStartPieceColor(final Piece piece) {
        if (!piece.isSameColor(turn)) {
            throw new IsNotPieceTurnException();
        }
    }

    public Color getTurn() {
        return turn;
    }
}
