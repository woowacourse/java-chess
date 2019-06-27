package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.MoveVector;

import java.util.List;

public class Knight extends SingleMovePiece {
    private static final List<MoveVector> directions;

    static {
        directions = MoveVector.knightDirection();
        ;
    }

    public Knight(ChessTeam team) {
        super(team, PieceInfo.Knight, directions);
    }
}
