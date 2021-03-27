package chess.domain.piece.strategy;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Position;

public class KnightMoveStrategy implements MoveStrategy{
    private static final String KNIGHT_ERROR = "[ERROR] 나이트의 이동 규칙에 어긋났습니다.";

    @Override
    public boolean canMove(Position source, Position target, ChessBoard chessBoard) {
        if (!canKnightMove(source, target)) {
            throw new IllegalArgumentException(KNIGHT_ERROR);
        }
        return true;
    }

    private boolean canKnightMove(Position source, Position target) {
        return ((Math.abs(source.subtractX(target)) == 2 && Math.abs(source.subtractY(target)) == 1) ||
                (Math.abs(source.subtractX(target)) == 1 && Math.abs(source.subtractY(target)) == 2));
    }
}
