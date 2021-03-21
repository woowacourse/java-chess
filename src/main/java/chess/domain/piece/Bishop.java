package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;

public class Bishop extends Piece {

    private Bishop(final Location location, final Team team) {
        super(location, team);
    }

    public static Bishop of(final Location location, final Team team) {
        return new Bishop(location, team);
    }

    @Override
    public void validateMovingAbilityToTarget(Location target) {
        if (!location.canMoveDigonallyTo(target)) {
            throw new IllegalArgumentException("[ERROR] 비숍은 해당 위치로 이동할 능력이 없습니다.");
        }
    }
}
