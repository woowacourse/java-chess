package chess.domain.piece;

import chess.domain.board.Position;

public class Rook extends Piece {

    public Rook(Team team, Position position) {
        super(team, position);
    }

    @Override
    public boolean canMove(Position targetPosition, Team team) {
        return position.isStraight(targetPosition)
                && isNotMyPosition(targetPosition)
                && isDifferentTeam(team);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move(Position targetPosition, Team nowPlayingTeam, Team targetTeam) {
        validate(targetPosition, nowPlayingTeam, targetTeam);
        return new Rook(team, targetPosition);
    }
}
