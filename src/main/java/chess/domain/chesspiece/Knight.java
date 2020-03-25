package chess.domain.chesspiece;

import chess.domain.Move;
import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

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
        return Move.makeKnightPath(this.position);
    }
}
