package chess.domain.piece;

import chess.domain.piece.position.PiecePosition;

public class Piece {

    private final Staunton staunton;
    private final PiecePosition position;
    private final Color color;

    public Piece(final Staunton staunton, final PiecePosition position, final Color color) {
        this.staunton = staunton;
        this.position = position;
        this.color = color;
    }
}
