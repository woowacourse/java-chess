package chess.domain.chesspiece;

import chess.domain.Move;
import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

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
        return Move.makePassablePathLengthOne(MoveRules.KING, this.position);
    }
}
