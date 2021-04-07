package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.score.PieceScore;
import chess.domain.team.Team;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final char SIGNATURE = 'p';

    private static final int BLACK_PAWN_INITIAL_Y = 7;
    private static final int WHITE_PAWN_INITIAL_Y = 2;

    private Pawn(final Location location, final Team team) {
        super(location, team);
    }

    private Pawn(long id, long roomId, Team team, Location location) {
        super(id, roomId, team, location);
    }

    public static Pawn of(Location location, Team team) {
        return new Pawn(location, team);
    }

    public static Pawn of(long id, long roomId, Team team, Location location) {
        return new Pawn(id, roomId, team, location);
    }

    @Override
    public void validateMovingAbilityToTarget(Location target) {
        List<Location> movableNextLocations = getNextLocations();

        boolean isMovable = movableNextLocations.stream()
            .anyMatch(location -> location.equals(target));
        if (!isMovable) {
            throw new IllegalArgumentException("[ERROR] 폰은 해당 위치로 이동할 능력이 없습니다.");
        }
    }

    private List<Location> getNextLocations() {
        List<Location> locations = new ArrayList<>();
        int dy = 1;
        if (team.isBlack()) {
            dy = -1;
        }
        for (int dx = -1; dx <= 1; dx++) {
            if (location.isRangeByStep(dx, 1)) {
                locations.add(location.moveByStep(dx, dy));
            }
        }
        if (isInitialLocation() && location.isRangeByStep(0, 2 * dy)) {
            locations.add(location.moveByStep(0, 2 * dy));
        }
        return locations;
    }

    private boolean isInitialLocation() {
        return (team.isBlack() && location.isSameY(BLACK_PAWN_INITIAL_Y))
            || (team.isWhite() && location.isSameY(WHITE_PAWN_INITIAL_Y));
    }

    @Override
    protected void validatePawnMovable(Location target, Board board) {
        if (isAnythingInFront(target, board)) {
            throw new IllegalArgumentException("[ERROR] 앞 위치에 기물(아군, 적 포함)있으면 이동할 수 없습니다.");
        }
        if (isEnemyOnTheFarwardDiagonal(target, board)) {
            throw new IllegalArgumentException("[ERROR] 적이 존재하지 않으므로 대각선으로 이동할 수 없습니다.");
        }
    }

    private boolean isAnythingInFront(Location target, Board board) {
        int subX = location.subtractX(target);
        int subY = location.subtractY(target);
        return subX == 0 && subY == 1 && board.isPieceExistIn(target);
    }

    private boolean isEnemyOnTheFarwardDiagonal(Location target, Board board) {
        int subX = location.subtractX(target);
        int subY = location.subtractY(target);
        return subX != 0 && subY == 1 && !board.isPieceExistIn(target);
    }

    @Override
    public PieceScore pieceScore() {
        return PieceScore.PAWN;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public char getSignature() {
        return SIGNATURE;
    }
}
