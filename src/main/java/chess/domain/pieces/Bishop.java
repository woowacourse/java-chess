package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.MoveVector;

import java.util.List;

public class Bishop extends RangeMovePiece {
    private static final List<MoveVector> directions;

    static {
        directions = MoveVector.diagonalDirection();
    }

    public Bishop(ChessTeam team) {
        super(team, PieceInfo.Bishop, directions);
    }
}
