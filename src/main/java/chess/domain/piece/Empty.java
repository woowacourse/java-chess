package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;
import chess.view.SymbolMatcher;

public class Empty extends Piece {
    public Empty(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }

    @Override
    public Point point() {
        return Point.EMPTY;
    }

    @Override
    public SymbolMatcher symbol() {
        return SymbolMatcher.EMPTY;
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        return false;
    }
}
