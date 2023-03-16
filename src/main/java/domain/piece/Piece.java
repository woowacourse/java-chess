package domain.piece;

<<<<<<< HEAD
import static domain.game.Side.BLACK;
import static domain.game.Side.WHITE;

import domain.game.Position;
import domain.game.Score;
import domain.game.Side;
=======
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
import java.util.List;

public abstract class Piece {
    protected final Side side;

    protected Piece(Side side) {
        this.side = side;
    }

    public abstract boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition);

<<<<<<< HEAD
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
        return sourcePosition.getPath(targetPosition);
    }

    public abstract boolean isEmptyPiece();

    public abstract PieceCategory getCategory();

    public abstract Score getScore();
=======
    public abstract List<Position> collectPath(Position sourcePosition, Position targetPosition);
>>>>>>> 3ad1dbf (refactor: 패키지 분리)

    public abstract boolean isEmptyPiece();

    public boolean isSameSideWith(Piece targetPiece) {
        return this.side == targetPiece.side;
    }

    public boolean isOpponentSideWith(Piece targetPiece) {
<<<<<<< HEAD
        return this.side.isOpponentWith(targetPiece.side);
    }

    public boolean isOpponentOf(Side side) {
        if (this.side.equals(WHITE)) {
            return side.equals(BLACK);
        }
        if (this.side.equals(BLACK)) {
            return side.equals(WHITE);
        }
        throw new IllegalStateException("서버 내부 에러 - Neutral한 Piece는 상대편을 확인할 수 없습니다.");
    }

    public boolean isSameSideOf(Side side) {
        if (this.side.equals(WHITE)) {
            return side.equals(WHITE);
        }
        if (this.side.equals(BLACK)) {
            return side.equals(BLACK);
        }
        throw new IllegalStateException("서버 내부 에러 - Neutral한 Piece는 상대편을 확인할 수 없습니다.");
=======
        if (this.side.equals(Side.WHITE)) {
            return targetPiece.side.equals(Side.BLACK);
        }
        return targetPiece.side.equals(Side.WHITE);
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
    }
}
