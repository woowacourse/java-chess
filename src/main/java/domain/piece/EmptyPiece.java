package domain.piece;

<<<<<<< HEAD
import domain.game.Position;
import domain.game.Score;
import domain.game.Side;
=======
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
import java.util.List;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(Side.NEUTRAL);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
<<<<<<< HEAD
        throw new IllegalStateException("서버 내부 에러 - EmptyPiece는 움직임을 확인할 수 없습니다.");
=======
        throw new UnsupportedOperationException("지원하지 않는 메서드 입니다.");
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
    }

    @Override
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
<<<<<<< HEAD
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

    @Override
    public Score getScore() {
        throw new IllegalStateException("서버 내부 에러 - EmptyPiece는 점수를 계산할 수 없습니다.");
=======
        throw new UnsupportedOperationException("지원하지 않는 메서드 입니다.");
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
    }
}
