package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.score.PieceScore;
import chess.domain.team.Team;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private static final char SIGNATURE = 'n';

    private Knight(final Location location, final Team team) {
        super(location, team);
    }

    private Knight(long id, long roomId, Team team, Location location) {
        super(id, roomId, team, location);
    }

    public static Knight of(final Location location, final Team team) {
        return new Knight(location, team);
    }

    public static Knight of(long id, long roomId, Team team, Location location) {
        return new Knight(id, roomId, team, location);
    }

    @Override
    public void validateMovingAbilityToTarget(Location target) {
        int subX = Math.abs(location.subtractX(target));
        int subY = Math.abs(location.subtractY(target));
        if (!((subX == 1 && subY == 2) || (subX == 2 && subY == 1))) {
            throw new IllegalArgumentException("[ERROR] 나이트는 해당 위치로 이동할 능력이 없습니다.");
        }
    }

    @Override
    public List<Location> findPathTo(Location target) {
        return Collections.emptyList();
    }

    @Override
    public PieceScore pieceScore() {
        return PieceScore.KNIGHT;
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
