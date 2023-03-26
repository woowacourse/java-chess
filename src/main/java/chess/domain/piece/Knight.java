package chess.domain.piece;

import chess.domain.board.Position;

public class Knight extends Piece {

    private static final int TWO_DIFFERENCE = 2;
    private static final int ONE_DIFFERENCE = 1;

    public Knight(Team team, Position position) {
        super(team, position, PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(Position targetPosition, Team team) {
        if (position.calculateColumnDistance(targetPosition) == TWO_DIFFERENCE) {
            return position.calculateRowDistance(targetPosition) == ONE_DIFFERENCE && isDifferentTeam(team);
        }
        if (position.calculateColumnDistance(targetPosition) == ONE_DIFFERENCE) {
            return position.calculateRowDistance(targetPosition) == TWO_DIFFERENCE && isDifferentTeam(team);
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
