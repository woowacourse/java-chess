package domain;

import domain.piece.Piece;
import domain.piece.TeamColor;

public class Rule {
    private TeamColor currentOrder;

    public Rule() {
        this.currentOrder = TeamColor.WHITE;
    }

    public void nextOrder() {
        if (currentOrder.isBlack()) {
            currentOrder = TeamColor.WHITE;
            return;
        }
        currentOrder = TeamColor.BLACK;
    }

    public void validateOrder(Piece piece) {
        if (isRightOrder(piece)) {
            return;
        }
        throw new IllegalArgumentException(String.format("현재 순서는 %s 입니다.", currentOrder));
    }

    private boolean isRightOrder(Piece piece) {
        return piece.isSameColor(currentOrder);
    }
}

