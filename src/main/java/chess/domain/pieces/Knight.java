package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.MoveVector;
import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private static final List<Direction> directions;

    static {
        directions = new ArrayList<>();
        directions.add(MoveVector.NightEastNorth.getDirection());
        directions.add(MoveVector.NightEastSouth.getDirection());
        directions.add(MoveVector.NightNorthEast.getDirection());
        directions.add(MoveVector.NightNorthWest.getDirection());
        directions.add(MoveVector.NightSouthEast.getDirection());
        directions.add(MoveVector.NightSouthWest.getDirection());
        directions.add(MoveVector.NightWestNorth.getDirection());
        directions.add(MoveVector.NightWestSouth.getDirection());
        ;
    }

    public Knight(ChessTeam team) {
        super(team, PieceInfo.Knight, directions);
    }

    @Override
    public Direction move(Point p1, Point p2) {
        Direction direction = p1.direction(p2);
        if (directions.contains(direction)) {
            return direction;
        }
        throw new IllegalArgumentException();
    }
}
