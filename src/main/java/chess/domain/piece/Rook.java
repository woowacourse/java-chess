package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;

public class Rook extends Piece {

    private Rook(final Location location, final Team team) {
        super(location, team);
    }

    public static Rook of(final Location location, final Team team) {
        return new Rook(location, team);
    }

    @Override
    public void validateMovingAbilityToTarget(Location target) {
        if (!location.canMoveHorizontallyOrVerticallyTo(target)) {
            throw new IllegalArgumentException("[ERROR] 룩은 해당 위치로 이동할 능력이 없습니다.");
        }
    }
}
