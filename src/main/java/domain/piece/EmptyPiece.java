package domain.piece;

import domain.game.Position;
import domain.game.Side;
import java.util.List;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(Side.NEUTRAL);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        throw new IllegalStateException("서버 내부 에러 - EmptyPiece는 움직임을 확인할 수 없습니다.");
    }

    @Override
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
        throw new IllegalStateException("서버 내부 에러 - EmptyPiece는 움직임의 경로를 계산할 수 없습니다.");
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
