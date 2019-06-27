package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.MoveVector;

import java.util.List;

public class Rook extends RangeMovePiece {
    private static final List<MoveVector> directions;

    static {
        directions = MoveVector.linearDirection();
    }


    public Rook(ChessTeam team) {
        super(team, PieceInfo.Rook, directions);
    }
}
