package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.PieceProperty;
import chess.domain.piece.state.PieceState;

public abstract class Piece {
    private final PieceProperty property;
    private PieceState state;

    public Piece(Color color, String name, double score, PieceState state) {
        this.property = PieceProperty.of(color, name, score);
        this.state = state;
    }

    public static double computeScore(List<Piece> pieces) {
        return PieceProperty.computeScore(
            pieces.stream()
                .map(piece -> piece.property)
                .collect(Collectors.toList()));
    }

    public List<Position> findMovablePaths(Position source, ChessBoard board) {
        return state.findMovablePositions(source, board);
    }

    public boolean isSameColor(Color color) {
        return property.isSameColor(color);
    }

    public boolean isSameColor(Piece piece) {
        return property.isSameColor(piece.property);
    }

    public String getName() {
        return property.getName();
    }

    public void updateState() {
        state = state.updateState();
    }

    public void killed() {
        state = state.die();
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
