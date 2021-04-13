package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.score.PieceScore;
import chess.domain.team.Team;

public class Bishop extends Piece {

    private static final char SIGNATURE = 'b';

    private Bishop(final Location location, final Team team) {
        super(location, team);
    }

    private Bishop(long id, long roomId, Team team, Location location) {
        super(id, roomId, team, location);
    }

    public static Bishop of(final Location location, final Team team) {
        return new Bishop(location, team);
    }

    public static Bishop of(long id, long roomId, Team team, Location location) {
        return new Bishop(id, roomId, team, location);
    }

    @Override
    public void validateMovingAbilityToTarget(Location target) {
        if (!location.canMoveDigonallyTo(target)) {
            throw new IllegalArgumentException("[ERROR] 비숍은 해당 위치로 이동할 능력이 없습니다.");
        }
    }

    @Override
    public PieceScore pieceScore() {
        return PieceScore.BISHOP;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public char getSignature() {
        return SIGNATURE;
    }
}
