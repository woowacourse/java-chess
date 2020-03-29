package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.route.Route;

import java.util.Map;

public class PawnMovingExecutor extends AbstractMovingExecutor {
    public PawnMovingExecutor(Piece piece) {
        super(piece);
    }

    @Override
    public void move(Route route, Map<Position, Piece> board, Position fromPosition, Position toPosition) {
        if (route.isToAttack()) {
            moveToAttack(board, fromPosition, toPosition);
            return;
        }

        if (route.isToProceed()) {
            moveToProceed(board, fromPosition, toPosition);
        }
    }

    private void moveToAttack(Map<Position, Piece> board, Position fromPosition, Position toPosition) {
        Piece pieceToAttack = board.get(toPosition);

        if (pieceToAttack == null || pieceToAttack.isTeam(piece.getTeam())) {
            throw new IllegalArgumentException("공격할 수 없습니다.");
        }

        board.remove(fromPosition);
        board.put(toPosition, piece);
    }

    private void moveToProceed(Map<Position, Piece> board, Position fromPosition, Position toPosition) {
        if (board.get(toPosition) == null) {
            board.remove(fromPosition);
            board.put(toPosition, piece);
        }
    }
}
