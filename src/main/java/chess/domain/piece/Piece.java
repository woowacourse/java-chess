package chess.domain.piece;

import java.util.List;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Name;
import chess.domain.piece.state.PieceState;

public abstract class Piece {
    private final Color color;
    private final Name name;
    private final double score;

    private PieceState pieceState;

    public Piece(Color color, String name, double score, PieceState pieceState) {
        this.color = color;
        this.name = new Name(color.convertName(name));
        this.score = score;
        this.pieceState = pieceState;
    }

    public static double computeScore(List<Piece> pieces) {
        return pieces.stream()
            .mapToDouble(piece -> piece.score)
            .sum();
    }

    public List<Position> findMovablePaths(Position source, ChessBoard board) {
        return pieceState.findMovablePositions(source, board);
    }

    public boolean isSameColor(Color other) {
        return color.isSameColor(other);
    }

    public boolean isSameColor(Piece piece) {
        return color.isSameColor(piece.color);
    }

    public String getName() {
        return name.getName();
    }

    public void updateState() {
        pieceState = pieceState.updateState();
    }

    public void killed() {
        pieceState = pieceState.killed();
    }

    public boolean isKing() {
        return new King(Color.White).isSame(this)
            || new King(Color.Black).isSame(this);
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
