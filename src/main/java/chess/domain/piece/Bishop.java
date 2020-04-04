package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.pathStrategy.BishopPathStrategy;

public class Bishop extends Piece {
    private static final String NAME = "b";
    private static final double SCORE = 3;

    public Bishop(PieceColor pieceColor, Position position) {
        super(NAME, SCORE, pieceColor, position, new BishopPathStrategy());
    }
}
