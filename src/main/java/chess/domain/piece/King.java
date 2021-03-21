package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Coordinate;

public class King extends Piece {
    private static final String NAME = "K";
    private static final double SCORE = 0;

    public King(TeamType teamType) {
        super(NAME, teamType, SCORE, Direction.getKingDirections());
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
