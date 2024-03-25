package chess.domain.piece;

import chess.domain.route.Route;
import chess.domain.position.Position;
import java.util.Objects;

public abstract class Piece {

    private final Side side;

    public Piece(Side side) {
        this.side = side;
    }

    protected abstract boolean hasFollowedRule(Position current, Position target, Route route);

    public void checkValidMove(Position source, Position target, Route route) {
        checkDifferentPosition(source, target);
        checkNoAllyPieceAtTarget(route.targetPiece());
        checkNoPathPieces(route);

        if (hasViolatedRule(source, target, route)) {
            throw new IllegalArgumentException("이동 규칙을 어기면 이동할 수 없습니다.");
        }
    }

    private void checkDifferentPosition(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("source 위치와 target 위치가 같으면 이동할 수 없습니다.");
        }
    }

    private void checkNoAllyPieceAtTarget(Piece targetPiece) {
        if (targetPiece.isSame(side)) {
            throw new IllegalArgumentException("target 위치에 같은 색의 기물이 존재하면 이동할 수 없습니다.");
        }
    }

    private void checkNoPathPieces(Route route) {
        if (route.notAllPathPiecesEmpty()) {
            throw new IllegalArgumentException("source 위치에서 target 위치까지의 경로에 기물이 존재하면 이동할 수 없습니다.");
        }
    }

    private boolean hasViolatedRule(Position source, Position target, Route route) {
        return !hasFollowedRule(source, target, route);
    }

    public boolean isBlack() {
        return side.isBlack();
    }

    public boolean isWhite() {
        return side.isWhite();
    }

    public boolean isSame(Side otherSide) {
        return side == otherSide;
    }

    public boolean isSameSide(Side otherSide) {
        return side == otherSide;
    }

    public boolean isOpposingSide(Side otherSide) {
        return side.opponent() == otherSide;
    }

    public boolean isEmpty() {
        return side == Side.EMPTY;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public boolean isNotSame(Side otherSide) {
        return !isSame(otherSide);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Piece piece = (Piece) object;
        return side == piece.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }
}
