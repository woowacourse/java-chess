package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.pathStrategy.KnightPathStrategy;

public class Knight extends Piece {
    private static final String NAME = "n";
    private static final double SCORE = 2.5;

    public Knight(PieceColor pieceColor, Position position) {
        super(NAME, SCORE, pieceColor, position, new KnightPathStrategy());
    }
}
