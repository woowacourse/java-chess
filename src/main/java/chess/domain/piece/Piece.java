package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import java.util.List;
import java.util.Map;

public abstract class Piece {

    private static final String PIECE_BLOCK = "가려는 위치 중간에 말이 존재합니다.";
    private static final String CANNOT_MOVE_SAME_COLOR = "아군이 있는 위치에 갈 수 없습니다.";

    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    protected abstract Direction matchDirection(Position from, Position to);

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public abstract double getScore();

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isBishop() {
        return false;
    }

    public boolean isRook() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public String getColor() {
        return color.name().toLowerCase();
    }

    public abstract String getImage();

    public void validateMove(Position from, Position to, Map<Position, Piece> pieces) {
        matchDirection(from, to);
        List<Position> path = from.backtrackPath(to);
        isNotBlocked(pieces, path);
        doesSatisfyColorCondition(to, pieces);
    }

    private void doesSatisfyColorCondition(Position to, Map<Position, Piece> pieces) {
        if (pieces.containsKey(to)) {
            validateSameColor(pieces.get(to));
        }
    }

    protected void validateSameColor(Piece toPiece) {
        if (toPiece.isSameColor(this.color)) {
            throw new IllegalArgumentException(CANNOT_MOVE_SAME_COLOR);
        }
    }

    protected void isNotBlocked(Map<Position, Piece> pieces, List<Position> path) {
        if (path.stream().anyMatch(pieces::containsKey)) {
            throw new IllegalArgumentException(PIECE_BLOCK);
        }
    }

    public abstract String getName();
}
