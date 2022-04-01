package chess.model.piece;

import chess.model.Direction;
import chess.model.Distance;
import chess.model.Position;
import chess.model.Team;
import chess.model.strategy.LimitedMoveStrategy;

import java.util.Collections;
import java.util.List;

public class King extends Piece {
    private static final String BLACK_NAME = "K";
    private static final String WHITE_NAME = "k";
    private static final double SCORE = 0D;

    public King(Team team) {
        super(team, new LimitedMoveStrategy(Direction.all(), Distance.oneStep()));
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }

    public List<Position> getIntervalPosition(Position source, Position target) {
        return Collections.emptyList();
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
