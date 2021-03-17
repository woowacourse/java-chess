package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;

public class Knight extends Piece {
    private static final String KNIGHT_NAME = "N";

    public Knight(Team team) {
        super(KNIGHT_NAME, team);
    }

    @Override
    public List<Position> movablePositions(Position target) {
        return null;
    }
}
