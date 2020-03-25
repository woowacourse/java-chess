package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

public class King extends ChessPiece {
    public King(Position position, Team team) {
        super("k", position, team);
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
