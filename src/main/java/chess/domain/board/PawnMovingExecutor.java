package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Placeable;
import chess.domain.position.Position;
import chess.domain.route.Route;

import java.util.Map;

public class PawnMovingExecutor extends AbstractMovingExecutor {
    public PawnMovingExecutor(Placeable placeable) {
        super(placeable);
    }

    @Override
    public void move(Route route, Map<Position, Placeable> board, Position fromPosition, Position toPosition) {
        if (route.isToAttack()) {
            moveToAttack(board, fromPosition, toPosition);
            return;
        }

        if (route.isToProceed()) {
            moveToProceed(board, fromPosition, toPosition);
        }
    }

    private void moveToAttack(Map<Position, Placeable> board, Position fromPosition, Position toPosition) {
        checkIfPossibleToAttack(board, toPosition);

        board.put(fromPosition, new Empty());
        board.put(toPosition, placeable);
    }

    private void checkIfPossibleToAttack(Map<Position, Placeable> board, Position toPosition) {
        Placeable pieceToAttack = board.get(toPosition);
        if (pieceToAttack.isEmpty() || pieceToAttack.isTeam(placeable.getTeam())) {
            throw new IllegalArgumentException("공격할 수 없습니다.");
        }
    }

    private void moveToProceed(Map<Position, Placeable> board, Position fromPosition, Position toPosition) {
        if (board.get(toPosition).isNotEmpty()) {
            throw new IllegalArgumentException("전진할 수 없습니다.");
        }

        board.put(fromPosition, new Empty());
        board.put(toPosition, placeable);
    }
}
