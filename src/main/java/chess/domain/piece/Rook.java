package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.score.PieceScore;
import chess.domain.team.Team;

public class Rook extends Piece {

    private static final char SIGNATURE = 'r';

    private Rook(final Location location, final Team team) {
        super(location, team);
    }

    private Rook(long id, long roomId, Team team, Location location) {
        super(id, roomId, team, location);
    }

    public static Rook of(final Location location, final Team team) {
        return new Rook(location, team);
    }

    public static Rook of(long id, long roomId, Team team, Location location) {
        return new Rook(id, roomId, team, location);
    }

    @Override
    public void validateMovingAbilityToTarget(Location target) {
        if (!location.canMoveHorizontallyOrVerticallyTo(target)) {
            throw new IllegalArgumentException("[ERROR] 룩은 해당 위치로 이동할 능력이 없습니다.");
        }
    }

    @Override
    public PieceScore pieceScore() {
        return PieceScore.ROOK;
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
