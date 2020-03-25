package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

public class Knight extends ChessPiece {
    public Knight(Position position, Team team) {
        super("n", position, team);
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }

    @Override
    public List<Position> makeCanMovePositions() {
        return null;
    }
}
