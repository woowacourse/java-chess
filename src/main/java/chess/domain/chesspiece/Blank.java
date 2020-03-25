package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

public class Blank extends ChessPiece {
    public Blank(Position position, Team team) {
        super(".", position, team);
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
