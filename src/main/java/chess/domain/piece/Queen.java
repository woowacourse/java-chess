package chess.domain.piece;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedQueen;

public final class Queen extends Piece {

    private static final String NAME = "q";

    public Queen(Color color) {
        super(color, NAME, new StartedQueen());
    }


    @Override
    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return null;
    }
}
