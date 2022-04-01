package chess.model.piece;

import chess.model.Direction;
import chess.model.Position;
import chess.model.Team;
import chess.model.strategy.UnlimitedMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static final String BLACK_NAME = "R";
    private static final String WHITE_NAME = "r";
    private static final double SCORE = 5D;
    private static final List<Direction> directions = Direction.linear();

    public Rook(Team team) {
        super(team, new UnlimitedMoveStrategy(Direction.linear()));
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
}
