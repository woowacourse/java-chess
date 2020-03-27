package chess.domain.piece;

import chess.domain.board.Position;

public class Pawn extends Piece {
    private static final String NAME = "p";

    public Pawn(PieceColor pieceColor, Position position) {
        super(NAME, pieceColor, position);
    }
}
