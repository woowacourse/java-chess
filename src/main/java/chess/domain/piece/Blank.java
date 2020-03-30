package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;
import chess.exception.PieceImpossibleMoveException;

public class Blank extends Piece {
    public static final String BLANK_MOVE_UNSUPPORTED_EXCEPTION_MESSAGE = "빈 칸은 이동할 수 없습니다.";

    public Blank(MoveStrategy moveStrategy, char representation, Team team, Position position) {
        super(moveStrategy, representation, team, position);
    }

    @Override
    public Piece movedPiece(Position toPosition) {
        throw new PieceImpossibleMoveException(BLANK_MOVE_UNSUPPORTED_EXCEPTION_MESSAGE);
    }

    @Override
    public double score() {
        return 0;
    }
}
