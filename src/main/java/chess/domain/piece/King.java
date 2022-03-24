package chess.domain.piece;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedKing;

public final class King extends Piece {
    private static final String NAME = "k";

    public King(Color color) {
        super(color, NAME, new StartedKing());
    }

    @Override
    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return null;
    }
}
