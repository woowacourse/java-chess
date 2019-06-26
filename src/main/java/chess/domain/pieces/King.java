package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.MoveVector;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final List<Direction> directions;

    static {
        directions = new ArrayList<>();
        directions.add(MoveVector.East.getDirection());
        directions.add(MoveVector.West.getDirection());
        directions.add(MoveVector.North.getDirection());
        directions.add(MoveVector.South.getDirection());
        directions.add(MoveVector.NorthEast.getDirection());
        directions.add(MoveVector.NorthWest.getDirection());
        directions.add(MoveVector.SouthEast.getDirection());
        directions.add(MoveVector.SouthWest.getDirection());
    }

    public King(ChessTeam team) {
        super(team, PieceInfo.King, directions);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
