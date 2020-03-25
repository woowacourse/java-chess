package chess.domain.chesspiece;

import chess.domain.Move;
import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

public class Bishop extends ChessPiece {
    public Bishop(Position position, Team team) {
        super("b", position, team);
    }

    @Override
    public boolean canMove(Position position) {
//        int x = this.position.getX() - position.getX();
//        int y = this.position.getY() - position.getY();
//        return x == y;
        List<Position> positions = makeCanMovePositions();
        System.out.println(positions.contains(position));

        return false;
    }

    @Override
    public List<Position> makeCanMovePositions() {
        return Move.makePassablePath(MoveRules.BISHOP, this.position);
    }
}
