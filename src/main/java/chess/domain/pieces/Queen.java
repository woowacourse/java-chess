package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.MoveVector;

import java.util.List;

public class Queen extends RangeMovePiece {
    private static final List<MoveVector> directions;

    static {
        directions = MoveVector.everyDirection();
    }

    public Queen(ChessTeam team) {
        super(team, PieceInfo.Queen, directions);
    }
}
