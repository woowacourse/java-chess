package chess.domain.piece;

import chess.domain.board.Position;

public class King extends Piece {

    private static final int BOUND = 1;

    public King(Team team, Position position) {
        super(team, position);
    }

    @Override
    public boolean canMove(Position targetPosition, Team team) {
        return isDifferenceUnderBound(targetPosition)
                && isNotMyPosition(targetPosition)
                && isNotSameTeam(team);
    }

    public boolean isDifferenceUnderBound(Position targetPosition) {
        return position.calculateColumnDifferenceWith(targetPosition) <= BOUND
                && position.calculateRowDifferenceWith(targetPosition) <= BOUND;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move(Position targetPosition, Team nowPlayingTeam, Team targetTeam) {
        validate(targetPosition, nowPlayingTeam, targetTeam);
        return new King(team, targetPosition);
    }
}
