package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class King extends OneStepPiece {

    public King(Team team, Position position) {
        super(team, position);
    }

    @Override
    public double getScore() {
        return 0;
    }

    @Override
    protected void validateIsPossibleDst(Position destination) {
        if (Math.abs(destination.getColDifference(position)) <= 1
                && Math.abs(destination.getRowDifference(position)) <= 1) {
            return;
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }

    @Override
    public boolean isKing() {
        return true;
    }
}