package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

public class Queen extends ChessPiece {
    public Queen(Position position, Team team) {
        super("q", position, team);
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
