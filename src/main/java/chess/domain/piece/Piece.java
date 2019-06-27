package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.Vectors;
import chess.domain.path.Path;

import java.util.Set;

public abstract class Piece {
    private final Path path;
    private PieceColor color;
    private PieceType type;

    public Piece(PieceColor color, Path path, PieceType type) {
        this.color = color;
        this.path = path;
        this.type = type;
    }

    public Vectors movableArea(Square source) {
        return path.movableArea(source);
    }

    public abstract double getScore();

    public boolean isSameColor(PieceColor pieceColor) {
        return color.equals(pieceColor);
    }
}
