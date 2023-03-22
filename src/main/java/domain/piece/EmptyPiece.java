package domain.piece;

import view.PieceCategory;

import java.util.List;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(Side.NEUTRAL);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        throw new UnsupportedOperationException("지원하지 않는 메서드 입니다.");
    }

    @Override
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
        throw new UnsupportedOperationException("지원하지 않는 메서드 입니다.");
    }

    @Override
    public boolean isEmptyPiece() {
        return true;
    }

    @Override
    public PieceCategory getCategory() {
        return PieceCategory.EMPTY_PIECE;
    }
}
