package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.score.PieceScore;
import chess.domain.team.Team;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private Knight(final Location location, final Team team) {
        super(location, team);
    }

    public static Knight of(final Location location, final Team team) {
        return new Knight(location, team);
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
}
