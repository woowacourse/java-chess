package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends ChessPiece {
    public Bishop(Position position, Team team) {
        super("b", position, team);
    }

    @Override
    public boolean canMove(Position position) {
        int x = this.position.getX() - position.getX();
        int y = this.position.getY() - position.getY();
        return x == y;
    }

    @Override
    public List<Position> makeCanMovePositions() {
        int x = this.position.getX();
        int y = this.position.getY();

    }
}
