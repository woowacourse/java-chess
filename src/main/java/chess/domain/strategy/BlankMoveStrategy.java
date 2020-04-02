package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.util.Direction;
import chess.exception.PieceImpossibleMoveException;

import java.util.List;

public class BlankMoveStrategy implements MoveStrategy {
    private static final String BLANK_MOVE_UNSUPPORTED_EXCEPTION_MESSAGE = "빈 칸은 움직일 수 없습니다.";

    @Override
    public Board movePieceWithTurnValidation(Board board, Piece fromPiece, Piece toPiece, Team currentTurn) {
        throw new PieceImpossibleMoveException(BLANK_MOVE_UNSUPPORTED_EXCEPTION_MESSAGE);
    }

    @Override
    public List<Position> getPossiblePositions(List<Piece> board, Piece piece) {
        throw new PieceImpossibleMoveException(BLANK_MOVE_UNSUPPORTED_EXCEPTION_MESSAGE);
    }

    @Override
    public List<Direction> getDirections() {
        throw new PieceImpossibleMoveException(BLANK_MOVE_UNSUPPORTED_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isInBoardRange(Position position) {
        return position.getX() <= Position.END_INDEX && position.getX() >= Position.START_INDEX &&
                position.getY() <= Position.END_INDEX && position.getY() >= Position.START_INDEX;
    }

    @Override
    public int boardIndexOf(Position position) {
        return (position.getY() - 1) * Position.ROW_SIZE + position.getX() - 1;
    }
}
