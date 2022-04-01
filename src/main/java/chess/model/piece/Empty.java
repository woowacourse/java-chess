package chess.model.piece;

import chess.model.Position;
import chess.model.Team;
import chess.model.strategy.NotMoveStrategy;

import java.util.List;

public class Empty extends Piece {

    private static final String NAME = ".";
    private static final double SCORE = 0D;

    public Empty() {
        super(Team.NONE, new NotMoveStrategy());
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public List<Position> getIntervalPosition(Position source, Position target) {
        throw new IllegalArgumentException("Empty 말은 움직일 수 없습니다.");
    }
}
