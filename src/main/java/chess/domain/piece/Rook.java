package chess.domain.piece;

import chess.domain.position.Position;
import chess.dto.PromotionOrder;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {

    public Rook(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            if (position.canMoveVertical(i)) {
                positions.add(position.moveVertical(i));
            }
            if (position.canMoveHorizontal(i)) {
                positions.add(position.moveHorizontal(i));
            }

            if (position.canMoveVertical(-i)) {
                positions.add(position.moveVertical(-i));
            }
            if (position.canMoveHorizontal(-i)) {
                positions.add(position.moveHorizontal(-i));
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
        chessPiece.castling(this);
    }

    @Override
    public ChessPiece promotion(final PromotionOrder promotionOrder) {
        throw new IllegalStateException("룩은 프로모션 불가능합니다.");
    }

    @Override
    public boolean isCheckmatedBy(final List<Position> allyPiecePositions, final List<ChessPiece> enemyPieces) {
        return false;
    }
}
