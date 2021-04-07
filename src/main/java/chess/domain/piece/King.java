package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.score.PieceScore;
import chess.domain.team.Team;

public class King extends Piece {

    private static final char SIGNATURE = 'k';

    private King(final Location location, final Team team) {
        super(location, team);
    }

    private King(long id, long roomId, Team team, Location location) {
        super(id, roomId, team, location);
    }

    public static King of(final Location location, final Team team) {
        return new King(location, team);
    }

    public static King of(long id, long roomId, Team team, Location location) {
        return new King(id, roomId, team, location);
    }

    @Override
    public void validateMovingAbilityToTarget(Location target) {
        if (!location.isAdjacent(target)) {
            throw new IllegalArgumentException("[ERROR] 킹은 해당 위치로 이동할 능력이 없습니다.");
        }
    }

    @Override
    public PieceScore pieceScore() {
        return PieceScore.KING;
    }

    @Override
    public boolean isKing() {
        return true;
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
