package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;

public class EmptyPiece extends Piece {
    private static final String NAME = ".";

    public EmptyPiece(PieceColor pieceColor, Position position) {
        super(NAME, pieceColor, position);
    }

    @Override
    public List<Position> getPathTo(Position target) {
        return null;
    }

    @Override
    protected void validateDistance(Position targetPosition) {
    }
}
