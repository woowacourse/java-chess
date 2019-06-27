package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

import java.util.ArrayList;

public class Empty extends AbstractSingleMovePiece {

    public Empty() {
        super(ChessTeam.EMPTY, PieceInfo.Empty, new ArrayList<>());
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
