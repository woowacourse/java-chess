package chess.domain.piece;

import chess.domain.route.Route;
import chess.domain.square.Square;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    private final Side side;

    public Piece(Side side) {
        this.side = side;
    }

    protected abstract boolean hasFollowedRule(Square current, Square target, Route route);

    public abstract PieceType pieceType();

    public void checkValidMove(Square source, Square target, Route route) {
        checkDifferentPosition(source, target);
        checkNoAllyPieceAtTarget(route.targetPiece());
        checkNoPathPieces(route);

        if (hasViolatedRule(source, target, route)) {
            throw new IllegalArgumentException("이동 규칙을 어기면 이동할 수 없습니다.");
        }
    }

    private void checkDifferentPosition(Square source, Square target) {
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

    private boolean hasViolatedRule(Square source, Square target, Route route) {
        return !hasFollowedRule(source, target, route);
    }

    public void checkBlockingPiece(Square target, Map<Square, Piece> pieces) {
        if (pieces.containsKey(target) && !pieces.get(target).isNotSame(this)) {
            throw new IllegalArgumentException("target 위치에 같은 팀 기물이 존재합니다.");
        }
        List<Square> positionsExceptTarget = filterPositionsExceptTarget(target, pieces);
        if (!positionsExceptTarget.isEmpty()) {
            throw new IllegalArgumentException("target 위치로 이동하는 경로에 기물이 존재합니다.");
        }
    }

    public boolean isRook() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isBishop() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
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

    public boolean isNotSame(Piece other) {
        return side != other.side;
    }

    public boolean isNotSame(Side otherSide) {
        return !isSame(otherSide);
    }

    private List<Square> filterPositionsExceptTarget(Square target, Map<Square, Piece> pieces) {
        return pieces.keySet().stream()
                .filter(key -> key != target)
                .toList();
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
