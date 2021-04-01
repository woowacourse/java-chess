package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;
import java.util.Arrays;
import java.util.List;

public class King extends Piece {

    private static final int INITIAL_X = 5;

    private King(final Location location, final Team team) {
        super(location, team);
    }

    private King(final int x, final Team team) {
        super(Location.of(x, getInitialY(team)), team);
    }

    public static King of(final Location location, final Team team) {
        return new King(location, team);
    }

    public static List<Piece> createInitialPieces() {
        return Arrays.asList(
            new King(INITIAL_X, Team.WHITE),
            new King(INITIAL_X, Team.BLACK)
        );
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    @Override
    public boolean isMovable(final Location target) {
        return location.isAdjacent(target);
    }
}
