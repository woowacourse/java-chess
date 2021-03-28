package chess.beforedb.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.KNIGHT;

import chess.beforedb.domain.board.Board;
import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;
import chess.beforedb.domain.position.MoveRoute;
import chess.beforedb.domain.position.Position;

public class Knight extends Piece {
    private static final double SCORE = 2.5;

    public Knight(TeamColor teamColor) {
        super(KNIGHT, teamColor, SCORE, Direction.knightDirections());
    }

    @Override
    public boolean canMoveTo(MoveRoute moveRoute, Board board) {
        if (isNotCorrectDirection(moveRoute.direction())) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        Position nextPosition = moveRoute.nextPositionOfStartPosition();
        if (!(moveRoute.isDestination(nextPosition)
            && board.isCellEmptyOrEnemyExists(nextPosition, getTeamColor()))) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        return true;
    }
}
