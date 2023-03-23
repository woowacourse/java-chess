package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends Piece {

    public Queen(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Team team) {
        return (isStraight(sourcePosition, targetPosition)
                || isDiagonal(sourcePosition, targetPosition))
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
        return new Queen(getTeam());
    }
}
