package chess.domain.chesspiece;

import chess.domain.Move;
import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

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
        return Move.makePassablePath(MoveRules.QUEEN, this.position);
    }
}
