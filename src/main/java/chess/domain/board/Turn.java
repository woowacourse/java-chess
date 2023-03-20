package chess.domain.board;

import chess.domain.piece.Color;

public class Turn {

    private Color turn = Color.WHITE;

    public void changeTurn() {
        if (turn == Color.WHITE) {
            turn = Color.BLACK;
            return;
        }
        turn = Color.WHITE;
    }

    public void validateStartPieceColor(final Color color) {
        if (color != turn) {
            throw new IllegalArgumentException("해당 말의 턴이 아닙니다.");
        }
    }
}
