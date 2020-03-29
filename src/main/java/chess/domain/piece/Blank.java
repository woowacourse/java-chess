package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.exception.PieceImpossibleMoveException;

import java.util.List;

public class Blank extends Piece {

    public static final String BLANK_MOVE_UNSUPPORT_EXCEPTION_MESSAGE = "빈 칸은 이동할 수 없습니다.";

    public Blank(char representation, Team team, Position position) {
        super(representation, team, position, PieceType.BLANK);
    }

    @Override
    public List<Position> getPossiblePositions(Board board) {
        throw new PieceImpossibleMoveException(BLANK_MOVE_UNSUPPORT_EXCEPTION_MESSAGE);
    }

    @Override
    public Piece movePiecePosition(Position toPosition) {
        throw new PieceImpossibleMoveException(BLANK_MOVE_UNSUPPORT_EXCEPTION_MESSAGE);
    }
}
