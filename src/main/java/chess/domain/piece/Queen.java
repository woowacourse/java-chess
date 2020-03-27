package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends Piece {
    private static final String NAME = "q";

    public Queen(PieceColor pieceColor, Position position) {
        super(NAME, pieceColor, position);
    }
}
