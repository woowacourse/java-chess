package chess.board.piece;

import chess.board.Team;
import chess.board.piece.position.Position;

import java.util.List;

public final class Empty extends Piece {

    private static final String NAME = ".";
    private static final double SCORE = 0D;
    private static final String TYPE = "empty";

    public Empty(Position position) {
        super(position, Team.NONE);
    }

    public Empty(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean isMovableRange(Position position) {
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public List<Position> getIntervalPosition(Piece targetPiece) {
        throw new IllegalArgumentException("[ERROR] 사이의 값을 셀수 없습니다.");
    }

}
