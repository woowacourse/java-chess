package chess.domain.piece;

import chess.domain.board.Position;

public class Knight extends Piece {

    private static final int TWO_DIFFERENCE = 2;
    private static final int ONE_DIFFERENCE = 1;

    public Knight(Team team, Position position) {
        super(team, position);
    }

    @Override
    public boolean canMove(Position targetPosition, Team team) {
        if (position.calculateColumnDifferenceWith(targetPosition) == TWO_DIFFERENCE) {
            return position.calculateRowDifferenceWith(targetPosition) == ONE_DIFFERENCE && isNotSameTeam(team);
        }
        if (position.calculateColumnDifferenceWith(targetPosition) == ONE_DIFFERENCE) {
            return position.calculateRowDifferenceWith(targetPosition) == TWO_DIFFERENCE && isNotSameTeam(team);
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move(Position targetPosition, Team nowPlayingTeam, Team targetTeam) {
        validate(targetPosition, nowPlayingTeam, targetTeam);
        return new Knight(team, targetPosition);
    }
}
