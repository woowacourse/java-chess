package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;
import chess.exception.PieceImpossibleMoveException;

import java.util.List;

public class BlankMoveStrategy implements MoveStrategy {
    private static final String BLANK_MOVE_UNSUPPORTED_EXCEPTION_MESSAGE = "빈 칸은 움직일 수 없습니다.";

    @Override
    public List<Position> getPossiblePositions(Board board, Piece piece) {
        throw new PieceImpossibleMoveException(BLANK_MOVE_UNSUPPORTED_EXCEPTION_MESSAGE);
    }

    @Override
    public List<Direction> getDirections() {
        throw new PieceImpossibleMoveException(BLANK_MOVE_UNSUPPORTED_EXCEPTION_MESSAGE);
    }
}
