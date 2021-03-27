package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Coordinate;
import chess.domain.result.Score;

import java.util.List;

public class LimitedMovablePiece extends Piece {

    protected LimitedMovablePiece(String name, TeamType teamType, Score score, List<Direction> directions) {
        super(name, teamType, score, directions);
    }

    @Override
    public boolean isMovable(ChessBoard chessBoard, Coordinate current, Coordinate destination) {
        Direction direction = current.evaluateDirection(destination);
        if (!isCorrectDirection(direction)) {
            return false;
        }
        Coordinate nextCoordinate = current.move(direction);
        return nextCoordinate.equals(destination) && chessBoard.isEmptyOrHasEnemyOn(destination, getTeamType());
    }
}
