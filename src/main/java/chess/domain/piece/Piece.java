package chess.domain.piece;

import chess.domain.board.Position;
import java.util.Objects;

public abstract class Piece {

    protected static final String INVALID_DIRECTION = "진행할 수 없는 방향입니다.";
    protected static final String INVALID_POSITION = "진행할 수 없는 위치입니다.";

    private final PieceType pieceType;
    private final Color color;

    protected Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public abstract Direction findValidDirection(Position current, Position target);

    public boolean isSamePiece(final PieceType expected) {
        return pieceType == expected;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece) o;
        return Objects.equals(pieceType, piece.pieceType) && Objects.equals(color, piece.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

}
