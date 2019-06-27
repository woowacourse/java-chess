package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.MoveVector;

import java.util.List;

public class King extends SingleMovePiece {
    private static final List<MoveVector> directions;

    static {
        directions = MoveVector.everyDirection();
    }

    public King(ChessTeam team) {
        super(team, PieceInfo.King, directions);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
