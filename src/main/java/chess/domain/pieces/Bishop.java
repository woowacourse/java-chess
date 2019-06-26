package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.MoveVector;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static final List<Direction> directions;

    static {
        directions = new ArrayList<>();
        directions.add(MoveVector.NorthEast.getDirection());
        directions.add(MoveVector.NorthWest.getDirection());
        directions.add(MoveVector.SouthEast.getDirection());
        directions.add(MoveVector.SouthWest.getDirection());
    }

    public Bishop(ChessTeam team) {
        super(team, PieceInfo.Bishop, directions);
    }
}
