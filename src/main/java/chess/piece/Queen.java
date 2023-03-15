package chess.piece;

import chess.piece.coordinate.Coordinate;

public class Queen extends Piece {
    public Queen(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'q';
    }
}
