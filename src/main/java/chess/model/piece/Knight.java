package chess.model.piece;

import chess.model.Position;
import chess.model.Team;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
    private static final String BLACK_NAME = "N";
    private static final String WHITE_NAME = "n";
    private static final double SCORE = 2.5D;

    public Knight(Position position, Team team) {
        super(position, team);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isKnightDirection(target);
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
        return Collections.emptyList();
    }
}
