package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;
import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {

    private static final int LEFT_INITIAL_X = 1;
    private static final int RIGHT_INITIAL_X = 8;

    private Rook(final Location location, final Team team) {
        super(location, team);
    }

    private Rook(final int x, final Team team) {
        super(Location.of(x, getInitialY(team)), team);
    }

    public static Rook of(final Location location, final Team team) {
        return new Rook(location, team);
    }

    public static List<Piece> createInitialPieces() {
        return Arrays.asList(
            new Rook(LEFT_INITIAL_X, Team.WHITE),
            new Rook(RIGHT_INITIAL_X, Team.WHITE),
            new Rook(LEFT_INITIAL_X, Team.BLACK),
            new Rook(RIGHT_INITIAL_X, Team.BLACK)
        );
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    @Override
    public boolean isMovable(final Location target) {
        return location.isHorizontalOrVertical(target);
    }
}
