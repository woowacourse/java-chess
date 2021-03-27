package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Coordinate;
import chess.domain.result.Score;

import java.util.List;

public class ConsecutiveMovablePiece extends Piece {

    protected ConsecutiveMovablePiece(String name, TeamType teamType, Score score, List<Direction> directions) {
        super(name, teamType, score, directions);
    }

    @Override
    public boolean isMovable(ChessBoard chessBoard, Coordinate current, Coordinate destination) {
        Direction direction = current.evaluateDirection(destination);
        if (!isCorrectDirection(direction)) {
            return false;
        }
        if (chessBoard.hasPieceOnRouteBeforeDestination(current, destination)) {
            return false;
        }
        return chessBoard.isEmptyOrHasEnemyOn(destination, getTeamType());
    }
}
