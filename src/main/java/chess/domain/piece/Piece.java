package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.PieceType;
import chess.domain.Position;
import java.util.Set;

public abstract class Piece {
    protected final Set<Direction> directions;
    private final Color color;
    private final PieceType pieceType;

    public Piece(Color color, PieceType pieceType, Set<Direction> directions) {
        this.color = color;
        this.pieceType = pieceType;
        this.directions = directions;
    }

    protected abstract Set<Position> calculateMovablePositions(Position currentPosition, Board board);

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isEmpty() {
        return getPieceType() == PieceType.NONE;
    }

    public boolean isSameColor(Piece piece) {
        return color.isSame(piece.color);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }
}
