package refactorChess.domain.piece;

import chess.domain.piece.attribute.Color;
import java.util.List;
import refactorChess.domain.board.Direction;
import refactorChess.domain.board.Position;

public abstract class Piece {

    private final PieceType pieceType;
    private final PieceColor pieceColor;
    private Position position;

    public Piece(PieceType pieceType, PieceColor pieceColor, Position position) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.position = position;
    }

    public MovePath findByMovePath(Piece piece) {
        validateColor(piece);
        final Direction direction = findByDirection(this.position, piece.position);
        validateDirection(direction, findByMovableDirection(piece, direction));
        return new MovePath(this.position, piece.position, direction);
    }

    private void validateColor(Piece piece) {
        if (!piece.isSameColor(piece)) {
            throw new IllegalArgumentException("목표 지점에 같은 팀의 기물이 존재합니다.");
        }
    }

    private boolean isSameColor(Piece piece) {
        return this.pieceColor == piece.pieceColor;
    }

    public boolean isSameColor(PieceColor pieceColor) {
        return this.pieceColor == pieceColor;
    }

    private void validateDirection(Direction direction, List<Direction> directions) {
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("해당 방향으로 기물이 움직일 수 없습니다.");
        }
    }

    public void move(Position position) {
        this.position = Position.change(position.getColumn(), position.getRow());
    }

    public boolean isBlank() {
        return this.pieceType == PieceType.NO_PIECE;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public Position getPosition() {
        return position;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    protected abstract Direction findByDirection(Position from, Position to);

    protected abstract List<Direction> findByMovableDirection(Piece piece, Direction direction);

    public abstract boolean isKing();

    public abstract boolean isPawn();

    @Override
    public String toString() {
        return "Piece{" +
                "pieceType=" + pieceType +
                ", pieceColor=" + pieceColor +
                ", position=" + position +
                '}';
    }
}
