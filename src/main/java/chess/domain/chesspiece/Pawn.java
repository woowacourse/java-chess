package chess.domain.chesspiece;

import chess.domain.Move;
import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

public class Pawn extends ChessPiece {
    boolean isFirstMove = true;

    public Pawn(Position position, Team team) {
        super("p", position, team);
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }

    @Override
    public List<Position> makeCanMovePositions() {

        return Move.makePassablePath(MoveRules.PAWN, this.position);
    }
}
