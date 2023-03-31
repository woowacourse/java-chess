package chess.domain.piece.move_rule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Collections;
import java.util.List;

public class WhitePawnMoveRule extends PawnMoveRule {
    private static final Rank WHITE_PAWN_INIT_RANK = Rank.RANK2;

    @Override
    public List<Position> move(Position currentPosition, Position nextPosition) {
        Position forwardOnePosition = currentPosition.moveRankUp();

        if (isTwoForwardMove(currentPosition, nextPosition)) {
            return List.of(forwardOnePosition);
        }
        if (!currentPosition.isNear(nextPosition)) {
            throw new IllegalArgumentException("폰이 갈 수 없는 위치입니다. 거리가 멉니다.");
        }
        if (!forwardOnePosition.isSameRank(nextPosition)) {
            throw new IllegalArgumentException("해당 진영의 폰이 갈 수 없는 방향입니다.");
        }
        return Collections.emptyList();
    }

    private boolean isInitialPosition(Position currentPosition) {
        return currentPosition.isSameRank(WHITE_PAWN_INIT_RANK);
    }

    private boolean isTwoForwardMove(Position currentPosition, Position nextPosition) {
        return isInitialPosition(currentPosition) && isTwoSquareForwardMove(currentPosition, nextPosition);
    }

    private boolean isTwoSquareForwardMove(Position currentPosition, Position nextPosition) {
        Position forwardTwoPosition = currentPosition.moveRankUp().moveRankUp();
        return forwardTwoPosition.equals(nextPosition);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.WHITE_PAWN;
    }
}
