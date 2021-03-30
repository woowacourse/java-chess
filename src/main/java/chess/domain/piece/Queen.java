package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {

    private static final int INITIAL_X = 4;

    private Queen(final Location location, final Team team) {
        super(location, team);
    }

    private Queen(final int x, final Team team) {
        super(Location.of(x, getInitialY(team)), team);
    }

    public static Queen of(final Location location, final Team team) {
        return new Queen(location, team);
    }

    public static List<Piece> createInitialPieces() {
        return Arrays.asList(
            new Queen(INITIAL_X, Team.WHITE),
            new Queen(INITIAL_X, Team.BLACK)
        );
    }

    @Override
    public boolean isMovable(final Location target) {
        return location.isHorizontalOrVertical(target) || location.isDiagonal(target);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }
}
