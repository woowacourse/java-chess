package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.MoveVector;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static final List<Direction> directions;

    static {
        directions = new ArrayList<>();
        directions.add(MoveVector.East.getDirection());
        directions.add(MoveVector.West.getDirection());
        directions.add(MoveVector.North.getDirection());
        directions.add(MoveVector.South.getDirection());
    }


    public Rook(ChessTeam team) {
        super(team, PieceInfo.Rook, directions);
    }
}
