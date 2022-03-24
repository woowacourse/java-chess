package chess.domain.piece;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedPawn;

public final class Pawn extends Piece {
    private static final String NAME = "p";

    public Pawn(Color color) {
        super(color, NAME, new StartedPawn());
    }

    @Override
    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return null;
    }
}
