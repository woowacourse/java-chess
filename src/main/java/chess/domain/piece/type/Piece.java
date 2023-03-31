package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.List;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final Color color;

    protected Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public final boolean isSameColor(final Color color) {
        return this.color.isSameColor(color);
    }

    public final List<Position> findRoute(Position start, Position end, Piece destinationPiece) {
        checkMovable(start, end, destinationPiece);
        return createRoute(start, end);
    }

    public final void checkMovable(Position start, Position end, Piece destinationPiece) {
        if (!isMovable(start, end, destinationPiece)) {
            throw new IllegalArgumentException("기물이 이동 할 수 있는 위치가 아닙니다");
        }
    }

    protected abstract boolean isMovable(Position start, Position end, Piece destinationPiece);

    public abstract List<Position> createRoute(Position start, Position end);

    public final double getScore() {
        return pieceType.getScore();
    }

    public final PieceType getPieceType() {
        return pieceType;
    }

    public final Color getColor() {
        return color;
    }

}
