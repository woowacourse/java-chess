package chess.domain.piece;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Name;
import chess.domain.piece.state.State;

public abstract class Piece {
    private final Color color;
    private final Name name;
    private State state;

    public Piece(Color color, String name, State state) {
        this.color = color;
        this.name = new Name(color.convertName(name));
        this.state = state;
    }

    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return state.getMovablePositions(source, board);
    }

    public boolean isSameColor(Color other) {
        return color.isSameColor(other);
    }

    public String getName() {
        return name.getName();
    }

    public Color getColor() {
        return color;
    }

    public void updateState() {
        state = state.updateState();
    }

    public void killed() {
        state = state.killed();
    }
}
