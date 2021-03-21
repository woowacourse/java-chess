package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Coordinate;

public class Knight extends Piece {
    private static final String NAME = "N";
    private static final double SCORE = 2.5;

    public Knight(TeamType teamType) {
        super(NAME, teamType, new Score(SCORE), Direction.getKnightDirections());
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
