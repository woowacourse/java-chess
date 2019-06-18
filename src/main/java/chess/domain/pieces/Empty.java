package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

public class Empty extends AbstractPiece {

    public Empty() {
        super(".", ChessTeam.EMPTY);
    }

    @Override
    public Direction move(Point p1, Point p2) {
        throw new IllegalArgumentException();
    }

    @Override
    public Direction attack(Point p1, Point p2) {
        throw new IllegalArgumentException();
    }


}
