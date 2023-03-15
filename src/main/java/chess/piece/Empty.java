package chess.piece;

import chess.piece.coordinate.Coordinate;

public class Empty extends Piece {
    public Empty(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'e';
    }
}
