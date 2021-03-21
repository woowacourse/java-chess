package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;

public class King extends Piece {

    private King(final Location location, final Team team) {
        super(location, team);
    }

    public static King of(final Location location, final Team team) {
        return new King(location, team);
    }

    @Override
    public void validateMovingAbilityToTarget(Location target) {
        if (!location.isAdjacent(target)) {
            throw new IllegalArgumentException("[ERROR] 킹은 해당 위치로 이동할 능력이 없습니다.");
        }
    }
}
