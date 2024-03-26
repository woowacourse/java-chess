package chess.domain.chessBoard;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;

public class Space {

    private Piece piece;
    private final Position position;

    public Space(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public void movePiece(Space targetSpace) {
        if (canCatch(targetSpace) || canMove(targetSpace)) {
            targetSpace.piece = piece;
            piece = new EmptyPiece();
            return;
        }
        throw new IllegalArgumentException("이동 규칙을 위반한 움직임입니다.");
    }

    private boolean canCatch(Space targetSpace) {
        return piece.canCatch(position, targetSpace.position) && piece.isOppositeColor(targetSpace.piece)
                && !piece.isSameColor(targetSpace.piece);
    }

    private boolean canMove(Space targetSpace) {
        return piece.canMove(position, targetSpace.position) && !piece.isSameColor(targetSpace.piece);
    }

    public List<Position> findRouteToTarget(Space targetSpace) {
        return position.findRoute(targetSpace.position);
    }

    public boolean isSamePosition(Position otherPosition) {
        return position.equals(otherPosition);
    }

    public boolean hasPiece() {
        return !piece.isSameColor(Color.EMPTY);
    }

    public Piece getPiece() {
        return piece;
    }
}
