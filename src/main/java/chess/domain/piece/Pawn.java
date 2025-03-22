package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.PromotionOrder;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        if (color.isWhite()) {
            if (position.canMoveUp()) {
                positions.add(position.moveUp());
            }
            if (position.row() == Row.TWO && position.canMoveUp(2)) {
                positions.add(position.moveUp(2));
            }
        }
        if (color.isBlack()) {
            if (position.canMoveDown()) {
                positions.add(position.moveDown());
            }

            if (position.row() == Row.SEVEN && position.canMoveDown(2)) {
                positions.add(position.moveDown(2));
            }
        }
        return positions;
    }

    @Override
    protected List<Position> calculateCanTakePositions() {
        final List<Position> positions = new ArrayList<>();
        if (color.isWhite()) {
            if (position.canMoveRightUp()) {
                positions.add(position.moveRightUp());
            }
            if (position.canMoveLeftUp()) {
                positions.add(position.moveLeftUp());
            }
        }
        if (color.isBlack()) {
            if (position.canMoveRightDown()) {
                positions.add(position.moveRightDown());
            }
            if (position.canMoveLeftDown()) {
                positions.add(position.moveLeftDown());
            }
        }
        return positions;
    }

    @Override
    public ChessPiece promotion(final PromotionOrder order) {
        if (!isPromotionable()) {
            throw new IllegalStateException("프로모션 할 수 없습니다.");
        }

        return switch (order) {
            case PromotionOrder.QUEEN -> new Queen(color, position);
            case PromotionOrder.ROOK -> new Rook(color, position);
            case PromotionOrder.BISHOP -> new Bishop(color, position);
            case PromotionOrder.KNIGHT -> new Knight(color, position);
        };
    }

    @Override
    public boolean isPromotionable() {
        return color.isWhite() && position.isTop() || color.isBlack() && position.isBottom();
    }

    @Override
    public void castling(final ChessPiece chessPiece) {
        throw new IllegalStateException("폰은 캐슬링 불가능합니다.");
    }
}
