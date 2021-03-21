package chess.domain.piece;

import static chess.domain.piece.type.PieceType.PAWN;

import chess.domain.board.Board;
import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;

public class Pawn extends Piece {
    private static final double DEFAULT_SCORE = 1;
    private static final double HALF_SCORE = DEFAULT_SCORE / 2;

    public Pawn(TeamColor teamColor) {
        super(PAWN, teamColor, DEFAULT_SCORE, Direction.pawnDirections(teamColor));
    }

    @Override
    public boolean isMovableTo(MoveRoute moveRoute, Board board) {
        Direction moveDirection = moveRoute.direction();
        if (isNotCorrectDirection(moveDirection)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        if (moveDirection.isForward()) {
            return isCanMoveForward(moveRoute, board);
        }
        return isCanMoveDiagonal(moveRoute, board);
    }

    private boolean isCanMoveForward(MoveRoute moveRoute, Board board) {
        if (moveRoute.isRankForwardedBy(2)) {
            return isCanMoveTwoRankForward(moveRoute, board);
        }
        if (moveRoute.isRankForwardedBy(1)) {
            return isCanMoveOneRankForward(moveRoute, board);
        }
        throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
    }


    private boolean isCanMoveTwoRankForward(MoveRoute moveRoute, Board board) {
        if (!moveRoute.isStartPositionFirstPawnPosition(color())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        if (board.isAnyPieceExistsOnRouteBeforeDestination(moveRoute)
            || board.isNotCellEmpty(moveRoute.destination())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }

    private boolean isCanMoveOneRankForward(MoveRoute moveRoute, Board board) {
        if (board.isNotCellEmpty(moveRoute.destination())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }

    private boolean isCanMoveDiagonal(MoveRoute moveRoute, Board board) {
        Position nextPosition = moveRoute.nextPositionOfStartPosition();
        if (!(board.isEnemyExists(moveRoute.destination(), color())
            && nextPosition.equals(moveRoute.destination()))) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }

    public static double defaultScore() {
        return DEFAULT_SCORE;
    }

    public static double halfScore() {
        return HALF_SCORE;
    }
}
