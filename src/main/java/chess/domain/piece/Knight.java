package chess.domain.piece;

import chess.domain.board.Position;

public class Knight extends Piece {
    private static final String NAME = "n";

    public Knight(PieceColor pieceColor, Position position) {
        super(NAME, pieceColor, position);
    }
}
