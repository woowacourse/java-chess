package chess.domain.piece;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedNight;

public final class Night extends Piece {
    private static final String NAME = "n";

    public Night(Color color) {
        super(color, NAME, new StartedNight());
    }

    @Override
    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return null;
    }
}
