package chess.domain.piece;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedBishop;

public final class Bishop extends Piece {
    private static final String NAME = "b";

    public Bishop(Color color) {
        super(color, NAME, new StartedBishop());
    }

    @Override
    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return null;
    }
}
