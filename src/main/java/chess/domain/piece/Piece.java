package chess.domain.piece;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.game.state.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.PieceProperty;
import chess.domain.piece.state.PieceState;

public abstract class Piece extends Named{
    private final PieceProperty property;
    private PieceState state;

    public Piece(Color color, String name, double score, PieceState state) {
        super(Name.of(name, color));
        this.property = PieceProperty.of(color, score);
        this.state = state;
    }

    public static double computeScore(List<Piece> pieces) {
        return PieceProperty.computeScore(
            pieces.stream()
                .map(piece -> piece.property)
                .collect(Collectors.toList()));
    }

    public List<Position> findMovablePositions(Position source, Map<Position, Piece> board) {
        return state.findMovablePositions(source, board);
    }

    public boolean isSameColor(Color color) {
        return property.isSameColor(color);
    }

    public boolean isSameColor(Piece piece) {
        return property.isSameColor(piece.property);
    }

    public void updateState() {
        state = state.updateState();
    }

    public void killed() {
        state = state.die();
    }

    public String getColor() {
        return property.getColor();
    }
}
