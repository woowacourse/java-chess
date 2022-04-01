package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private static final int TWO_FOR_NO_BETWEEN = 2;
    public static final Piece EMPTY = new Empty();

    private static class Empty extends Piece {
        public Empty() {
            super(Color.EMPTY);
        }

        @Override
        public boolean movable(Position from, Position to, Board board) {
            return false;
        }
    }

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean movable(Position from, Position to, Board board);

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public int angle(Position from, Position to) {
        return (int) (Math.atan2(from.dy(to), from.dx(to)) * (180.0 / Math.PI));
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(Piece another) {
        return this.color == another.color;
    }

    protected void validateAngle(List<Integer> validAngles, Position from, Position to) {
        if (!validAngles.contains(angle(from, to))) {
            throw new IllegalStateException();
        }
    }

    protected void validatePieceOnWay(Position from, Position to, Board board) {
        if (Math.abs(from.dx(to)) + Math.abs(from.dy(to)) <= TWO_FOR_NO_BETWEEN) { // 중간 포지션이 존재하지 않는 1칸 이동의 경우 검증 탈출
            return;
        }

        if (from.between(to)
                .stream()
                .anyMatch(board::exists)) {
            throw new IllegalStateException();
        }
    }

    protected void validateTarget(Board board, Position to) {
        if (board.isSameColorOnTarget(this, to)) {
            throw new IllegalStateException("목적지에 아군 기물이 존재합니다");
        }
    }

    public boolean isEnemy(Piece piece) {
        return this.color.opposite() == piece.color;
    }

    public boolean isKing() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
