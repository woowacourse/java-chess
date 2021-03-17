package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;

public class Pawn extends Piece {
    private static final String PAWN_NAME = "P";

    public Pawn(Team team) {
        super(PAWN_NAME, team);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        return null;
    }
}
