package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;

public class King extends Piece {
    private static final String KING_NAME = "K";

    public King(Team team) {
        super(KING_NAME, team);
    }

    @Override
    public List<Position> movablePositions(Position target) {
        return null;
    }
}
