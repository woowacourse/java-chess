package chess.domain.piece;

import chess.domain.board.Position;

public class Bishop extends Piece {

    public Bishop(Team team, Position position) {
        super(team, position, PieceType.BISHOP);
    }

    @Override
    public boolean canMove(Position targetPosition, Team team) {
        return position.isDiagonal(targetPosition) && isNotMyPosition(targetPosition)
                && isDifferentTeam(team);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move(Position targetPosition, Team nowPlayingTeam, Team targetTeam) {
        validate(targetPosition, nowPlayingTeam, targetTeam);
        return new Bishop(team, targetPosition);
    }
}
