package chess.domain.piece;

import chess.domain.board.Position;

public class Rook extends Piece {

    public Rook(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Team team) {
        return isStraight(sourcePosition, targetPosition)
                && isNotMyPosition(sourcePosition, targetPosition)
                && isNotSameTeam(team);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move(Position sourcePosition, Position targetPosition, Team nowPlayingTeam, Team targetTeam) {
        validate(sourcePosition, targetPosition, nowPlayingTeam, targetTeam);
        return new Rook(getTeam());
    }
}
