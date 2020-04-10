package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.pathStrategy.KingPathStrategy;

public class King extends Piece {
    private static final String NAME = "k";
    private static final double SCORE = 0;

    public King(PieceColor pieceColor, Position position) {
        super(NAME, SCORE, pieceColor, position, new KingPathStrategy());
    }
}
