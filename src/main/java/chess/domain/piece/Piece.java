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
    private PieceState pieceState;

    public Piece(Color color, String name, PieceState pieceState) {
        this.color = color;
        this.name = new Name(color.convertName(name));
        this.pieceState = pieceState;
    }

    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return pieceState.getMovablePositions(source, board);
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
        pieceState = pieceState.updateState();
    }

    public void killed() {
        pieceState = pieceState.killed();
    }
}
