package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.strategy.MoveStrategy;
import java.util.List;

public abstract class Piece {

    public static final String CANNOT_MOVE = "[ERROR] 이동할 수 없는 방향입니다.";

    private final Color color;
    private final MoveStrategy moveStrategy;
    protected Type type;

    public Piece(Color color, MoveStrategy moveStrategy) {
        this.color = color;
        this.moveStrategy = moveStrategy;
    }

    public boolean isMovable(
        ChessBoard chessBoard,
        Position sourcePosition,
        Position targetPosition
    ) {
        return moveStrategy.movable(chessBoard, sourcePosition, targetPosition, this);
    }

    public String getName() {
        return type.nameByColor(color);
    }

    public Color getColor() {
        return color;
    }

    public abstract List<Direction> direction();

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public boolean isSameColor(Piece piece) {
        return this.color.equals(piece.color);
    }

    public boolean isBlank() {
        return type == Type.BLANK;
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isKing() {
        return type.equals(Type.KING);
    }

    public boolean isPawn() {
        return type.equals(Type.PAWN);
    }

    public double score() {
        return type.getScore();
    }

    public void validateDirection(Direction direction) {
        List<Direction> directions = this.direction();
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException(CANNOT_MOVE);
        }
    }
}
