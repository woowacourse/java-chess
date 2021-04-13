package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.score.PieceScore;
import chess.domain.team.Team;

public class Queen extends Piece {

    private static final char SIGNATURE = 'q';

    private Queen(final Location location, final Team team) {
        super(location, team);
    }

    private Queen(long id, long roomId, Team team, Location location) {
        super(id, roomId, team, location);
    }

    public static Queen of(Location location, Team team) {
        return new Queen(location, team);
    }

    public static Queen of(long id, long roomId, Team team, Location location) {
        return new Queen(id, roomId, team, location);
    }

    @Override
    public void validateMovingAbilityToTarget(Location target) {
        if (!(location.canMoveHorizontallyOrVerticallyTo(target) || location.canMoveDigonallyTo(target))) {
            throw new IllegalArgumentException("[ERROR] 퀸은 해당 위치로 이동할 능력이 없습니다.");
        }
    }

    @Override
    public PieceScore pieceScore() {
        return PieceScore.QUEEN;
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
