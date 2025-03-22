package chess.domain.piece;

import chess.domain.position.Position;
import chess.dto.PromotionOrder;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            if (position.canMoveDiagonal(i, i)) {
                positions.add(position.moveDiagonal(i, i));
            }
            if (position.canMoveDiagonal(i, -i)) {
                positions.add(position.moveDiagonal(i, -i));
            }
            if (position.canMoveDiagonal(-i, i)) {
                positions.add(position.moveDiagonal(-i, i));
            }
            if (position.canMoveDiagonal(-i, -i)) {
                positions.add(position.moveDiagonal(-i, -i));
            }
        }

        return positions;
    }

    @Override
    protected List<Position> calculateCanTakePositions() {
        return calculateCanMovePositions();
    }

    @Override
    public boolean isPromotionable() {
        return false;
    }

    @Override
    public void castling(final ChessPiece chessPiece) {
        throw new IllegalStateException("비숍은 캐슬링 불가능합니다.");
    }

    @Override
    public ChessPiece promotion(final PromotionOrder promotionOrder) {
        throw new IllegalStateException("비숍은 프로모션 불가능합니다.");
    }
}
