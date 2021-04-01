package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;
import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {

    private static final int LEFT_INITIAL_X = 3;
    private static final int RIGHT_INITIAL_X = 6;

    private Bishop(final Location location, final Team team) {
        super(location, team);
    }

    private Bishop(final int x, final Team team) {
        super(Location.of(x, getInitialY(team)), team);
    }

    public static Bishop of(final Location location, final Team team) {
        return new Bishop(location, team);
    }

    public static List<Piece> createInitialPieces() {
        return Arrays.asList(
            new Bishop(LEFT_INITIAL_X, Team.WHITE),
            new Bishop(RIGHT_INITIAL_X, Team.WHITE),
            new Bishop(LEFT_INITIAL_X, Team.BLACK),
            new Bishop(RIGHT_INITIAL_X, Team.BLACK)
        );
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public boolean isMovable(final Location target) {
        return location.isDiagonal(target);
    }
}
