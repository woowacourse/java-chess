package chess.model.piece;

import chess.model.Direction;
import chess.model.Distance;
import chess.model.Position;
import chess.model.Team;
import chess.model.strategy.LimitedMoveStrategy;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
    private static final String BLACK_NAME = "N";
    private static final String WHITE_NAME = "n";
    private static final double SCORE = 2.5D;

    public Knight(Team team) {
        super(team, new LimitedMoveStrategy(Direction.knight(), Distance.oneStep()));
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

    @Override
    public List<Position> getIntervalPosition(Position source, Position target) {
        return Collections.emptyList();
    }
}
