package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    public static final Piece EMPTY = new Empty();
    private static final int TWO_FOR_NO_BETWEEN = 2;
    private static final String SAME_COLOR_PIECE_IS_ON_TARGET = "목적지에 아군 기물이 존재합니다";
    private static final String CAN_NOT_MOVE_FOR_PIECE_ON_THE_WAY = "이동 경로에 기물이 존재합니다";
    private static final String CAN_NOT_MOVE_TO_THE_DIRECTION = "이동할 수 없는 방향입니다";

    private static class Empty extends Piece {
        public static final String EMPTY_PIECE_STRING = ".";

        public Empty() {
            super(Color.EMPTY);
        }

        @Override
        public MoveResult movable(Position from, Position to, Board board) {
            return MoveResult.FAIL;
        }

        @Override
        public double getScore() {
            return 0;
        }

        @Override
        public String getName() {
            return EMPTY_PIECE_STRING;
        }
    }

    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract MoveResult movable(Position from, Position to, Board board);

    public abstract double getScore();

    public abstract String getName();

    protected void validateAngle(List<Integer> validAngles, Position from, Position to) {
        if (!validAngles.contains(angle(from, to))) {
            throw new IllegalStateException(CAN_NOT_MOVE_TO_THE_DIRECTION);
        }
    }

    private int angle(Position from, Position to) {
        return (int) (Math.atan2(from.dy(to), from.dx(to)) * (180.0 / Math.PI));
    }

    protected void validatePieceOnWay(Position from, Position to, Board board) {
        if (Math.abs(from.dx(to)) < TWO_FOR_NO_BETWEEN
                && Math.abs(from.dy(to)) < TWO_FOR_NO_BETWEEN) { // 중간 포지션이 존재하지 않는 1칸 이동의 경우 검증 탈출
            return;
        }

        if (from.between(to)
                .stream()
                .anyMatch(board::exists)) {
            throw new IllegalStateException(CAN_NOT_MOVE_FOR_PIECE_ON_THE_WAY);
        }
    }

    protected void validateTarget(Board board, Position to) {
        if (board.isSameColorOnTarget(this, to)) {
            throw new IllegalStateException(SAME_COLOR_PIECE_IS_ON_TARGET);
        }
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(Piece another) {
        return this.color == another.color;
    }

    public boolean isEnemy(Piece piece) {
        return this.color.opposite() == piece.color;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
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
