package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

public class Rook extends ChessPiece {
    public Rook(Position position, Team team) {
        super("r", position, team);
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
