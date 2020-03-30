package chess.piece.type.movable;

import chess.location.Location;
import chess.piece.type.Piece;
import chess.team.Team;

import java.util.Map;

public class PawnPieceMovable implements PieceMovable {
    private static final int WHITE_PAWN_ROW = 2;
    private static final int BLACK_PAWN_ROW = 7;

    private final Team team;

    public PawnPieceMovable(Team team) {
        this.team = team;
    }

    @Override
    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
        int value = 1;
        if (team.isBlack()) {
            value = -1;
        }
        // 대각선 이동
        if (now.isForwardDiagonal(after, value) && canMoveDiagonal(board, now, after)) {
            return true;
        }
        // now가 초기위치일 경우 두 칸 혹은 한 칸 이동 가능
        // 이동하려는 곳 혹은 가는 길에 Piece가 있으면 불가능
        if (isInitialPawnLocation(now, team)
                && isInitialPawnForwardRange(now, after, value)
                && hasNotObstacle(board, now, after)) {
            return true;
        }

        // 한칸 이동 가능
        if (isPawnForwardRange(now, after, value) && hasNotObstacle(board, now, after)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasNotObstacle(Map<Location, Piece> board, Location now, Location after) {
        // 한 칸 움직이는 명령
        Location nextLocation = now.calculateNextLocation(after, 1);
        boolean isMoveByOnceRowCommand = Math.abs(now.getRowValue() - after.getRowValue()) == 1;
        // 진행 방향으로 한 칸 이동했을 때 막혀있으면 장애물로 판단
        if (isMoveByOnceRowCommand && board.containsKey(nextLocation)) {
            return false;
        }
        // 두 칸 움직이는 명령
        boolean isMoveByTwiceRowCommand = Math.abs(now.getRowValue() - after.getRowValue()) == 2;
        boolean hasNotObstacle = board.containsKey(nextLocation) || board.containsKey(after);
        if (isMoveByTwiceRowCommand && hasNotObstacle) {
            // 진행 방향으로 두 칸 이동했을 때 막혀있으면 장애물로 판단
            return false;
        }
        return true;
    }

    public boolean canMoveDiagonal(Map<Location, Piece> board, Location now, Location after) {
        // 대각선 방향 && 목적지에 피스가 있는경우
        if(now.isDiagonal(after)) {
            if(board.containsKey(after)) {
                Piece maybeEnemyPiece = board.get(after);
                return maybeEnemyPiece.isReverseTeam(team);
            }
            return true;
        }
        return false;
    }

    private boolean isInitialPawnLocation(Location location, Team team) {
        if (team.isBlack()) {
            return location.isSameRow(BLACK_PAWN_ROW);
        }
        return location.isSameRow(WHITE_PAWN_ROW);
    }

    // 2칸 혹은 한 칸이동할 수 있다.
    private boolean isInitialPawnForwardRange(Location now, Location after, int value) {
        Location onceMovedRowByValue = now.plusRowBy(value);
        Location TwiceMovedRowByValue = now.plusRowBy(value * 2);

        boolean onceOrTwiceRowMoved =
                onceMovedRowByValue.isSameRow(after) || TwiceMovedRowByValue.isSameRow(after);

        return onceOrTwiceRowMoved && now.isSameCol(after);
    }

    private boolean isPawnForwardRange(Location now, Location after, int value) {
        Location movedRowByValue = now.plusRowBy(value);

        return movedRowByValue.isSameRow(after)
                && movedRowByValue.isSameCol(after);
    }
}
