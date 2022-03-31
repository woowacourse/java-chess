package chess.domain.piece;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Name;
import chess.domain.piece.state.PieceState;
import java.util.List;

public abstract class Piece {
    private final Color color;
    private final Name name;
    private final double score;

    private PieceState pieceState;

    public Piece(Color color, String name, double score, PieceState pieceState) {
        this.color = color;
        if (color == Color.BLACK) {
            name = name.toUpperCase();
        }
        this.name = new Name(name);
        this.score = score;
        this.pieceState = pieceState;
    }

    public static double computeScore(List<Piece> pieces) {
        return pieces.stream()
            .mapToDouble(piece -> piece.score)
            .sum();
    }

    public boolean isSameColor(Color other) {
        return color.isSameColor(other);
    }

    public void updateState() {
        pieceState = pieceState.updateState();
    }

    public void die() {
        pieceState = pieceState.die();
    }

    public abstract boolean isKing();

    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return pieceState.getMovablePositions(source, board);
    }

    public String getName() {
        return name.getName();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Piece piece = (Piece)o;

        return getName() != null ? getName().equals(piece.getName()) : piece.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
