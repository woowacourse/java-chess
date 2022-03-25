package chess.piece;

import chess.Position;
import chess.Team;

import java.util.List;

public class Queen extends Piece {
    private static final String BLACK_NAME = "Q";
    private static final String WHITE_NAME = "q";

    public Queen(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean isMovable(Position position) {
        return isCorrectDirection(position);
    }

    @Override
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }

    @Override
    public List<Position> getIntervalPosition(Piece targetPiece) {
        return null;
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isVertical(position) ||
                this.position.isHorizontal(position) ||
                this.position.isDiagonal(position);
    }
}
