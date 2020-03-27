package chess.piece.type;

import java.util.Map;

import chess.board.Location;
import chess.score.Score;
import chess.team.Team;

public class Pawn extends Piece {
    private static final char name = 'p';
    private static final int PAWN_SCORE = 1;

    public Pawn(Team team) {
        super(changeName(team), new Score(PAWN_SCORE));
    }

    private static char changeName(Team team) {
        if (team.isBlack()) {
            return Character.toUpperCase(name);
        }
        return name;
    }

    @Override
    public boolean canMove(Location now, Location after) {
        int value = 1;
        if (isBlack()) {
            value = -1;
        }
        // now가 초기위치인지 검사
        if (now.isInitialPawnLocation(isBlack())) {
            return now.isInitialPawnForwardRange(after, value) || now.isForwardDiagonal(after, value);
        }
        return now.isForwardDiagonal(after, value) || now.isPawnForwardRange(after, value);
    }

    @Override
    public boolean hasObstacle(Map<Location, Piece> board, Location now, Location destination) {
        return !isMovable(board, now, destination);
    }

    private boolean isMovable(Map<Location, Piece> board, Location now, Location destination) {
        // 대각선 방향 && 목적지에 피스가 있는경우
        if (now.isDiagonal(destination) && board.containsKey(destination)) {
            Piece maybeEnemyPiece = board.get(destination);
            return maybeEnemyPiece.isSameTeam(!this.isBlack());
        }

        Location nextLocation = now.calculateNextLocation(destination, 1);
        return (!board.containsKey(destination) && !board.containsKey(nextLocation))
                && now.isVertical(destination);
    }
}
