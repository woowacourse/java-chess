package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public abstract class UnJumpableMoveRule implements MoveRule {
    protected void validateRoute(Map<Position, Piece> board, List<Position> route) {
        if (isExistAnotherPiece(board, route)) {
            throw new IllegalArgumentException("경로상에 다른 기물이 있어 움직일 수 없습니다.");
        }
    }

    private boolean isExistAnotherPiece(Map<Position, Piece> board, List<Position> route){
        return route.stream().anyMatch(position -> board.containsKey(position));
    }
}
