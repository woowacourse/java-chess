package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.pathStrategy.RookPathStrategy;

public class Rook extends Piece {
    private static final String NAME = "r";
    private static final double SCORE = 5;

    public Rook(PieceColor pieceColor, Position position) {
        super(NAME, SCORE, pieceColor, position, new RookPathStrategy());
    }
}
