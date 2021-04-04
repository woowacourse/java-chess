package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.feature.Type;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    public static final String NOT_MOVABLE_POSITION_ERROR = "이동할 수 없는 위치입니다.";
    public static final String NO_COLOR_ERROR = "색깔은 흑이나 백이어야 합니다.";

    private final Color color;
    protected Type type;
    private Position position;

    protected Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    protected Piece(Position position) {
        this.color = Color.NO_COLOR;
        this.position = position;
    }

    public String getName() {
        return type.nameByColor(color);
    }

    private Color color() {
        return color;
    }

    public boolean isNotAlly(Piece piece) {
        return !this.color().equals(piece.color());
    }

    public boolean isSameColor(Color color) {
        return this.color().equals(color);
    }

    public boolean isBlank() {
        return type.equals(Type.BLANK);
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isPawn() {
        return type.equals(Type.PAWN);
    }

    public boolean isKing() {
        return type.equals(Type.KING);
    }

    public double score() {
        return type.getScore();
    }

    public void move(ChessBoard chessBoard, Direction direction, Position targetPosition) {
        if (isMovable(chessBoard, direction, targetPosition)) {
            chessBoard.replace(this.position, new Blank(this.position));
            this.position = targetPosition;
            chessBoard.replace(targetPosition, this);
            return;
        }
        throw new IllegalArgumentException(NOT_MOVABLE_POSITION_ERROR);
    }

    public Position nextPosition(Direction direction) {
        return position.nextPosition(direction);
    }

    public String getType() {
        return type.getName();
    }

    public String getColor() {
        return color.getColor();
    }

    public String getPosition() {
        return position.getPosition();
    }

    public abstract boolean isMovable(ChessBoard chessBoard, Direction direction, Position targetPosition);

    public abstract List<Direction> directions();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return color == piece.color && type == piece.type && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }
}
