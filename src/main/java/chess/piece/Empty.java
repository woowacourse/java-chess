package chess.piece;

import chess.piece.position.Position;
import chess.Team;

import java.util.List;

public final class Empty extends Piece {

    private static final String NAME = ".";
    private static final double SCORE = 0D;

    public Empty(Position position) {
        super(position, Team.NONE);
    }

    @Override
    public boolean isMovable(Position position) {
        return false;
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
    public List<Position> getIntervalPosition(Piece targetPiece) {
        throw new IllegalArgumentException("사이의 값을 셀수 없습니다.");
    }

}
