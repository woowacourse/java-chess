package chess.domain.piece;

import chess.domain.board.Coordinate;
import chess.domain.board.Pieces;

public class EmptyPiece extends AbstractPiece {

    private static final Piece INSTANCE = new EmptyPiece();

    private EmptyPiece() {
        super(PieceType.EMPTY, Team.EMPTY);
    }

    public static Piece getInstance() {
        return INSTANCE;
    }

    @Override
    void validatePieceMoveRule(Coordinate source, Coordinate target, Pieces pieces) {
        throw new UnsupportedOperationException();
    }
}
