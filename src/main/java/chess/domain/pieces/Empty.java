package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

import java.util.List;

public class Empty extends AbstractPiece {

    public Empty() {
        super(".", ChessTeam.EMPTY);
    }

    @Override
    public List<Direction> range(Point p1, Point p2, Piece piece) {
        throw new IllegalArgumentException();
    }
}
