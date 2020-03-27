package chess.domain.piece;

import chess.domain.board.Position;

public class King extends Piece {
    private static final String NAME = "k";

    public King(PieceColor pieceColor, Position position) {
        super(NAME, pieceColor, position);
    }
}
