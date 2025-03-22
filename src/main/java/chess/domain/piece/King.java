package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {

    public King(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        if (position.canMoveLeft()) {
            positions.add(position.moveLeft());
        }
        if (position.canMoveRight()) {
            positions.add(position.moveRight());
        }
        if (position.canMoveUp()) {
            positions.add(position.moveUp());
        }
        if (position.canMoveDown()) {
            positions.add(position.moveDown());
        }
        if (position.canMoveLeftUp()) {
            positions.add(position.moveLeftUp());
        }
        if (position.canMoveLeftDown()) {
            positions.add(position.moveLeftDown());
        }
        if (position.canMoveRightUp()) {
            positions.add(position.moveRightUp());
        }
        if (position.canMoveRightDown()) {
            positions.add(position.moveRightDown());
        }
        if (position.canMoveRightUp()) {
            positions.add(position.moveRightUp());
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
        if (!canCastling(chessPiece)) {
            throw new IllegalStateException("한번도 움직이지 않아야 캐슬링 가능합니다.");
        }

        if (isLeftThenRook(chessPiece)) {
            move(position.moveRight(2));
            chessPiece.move(position.moveLeft());
        } else {
            move(position.moveLeft(2));
            chessPiece.move(position.moveRight());
        }

    }

    private boolean canCastling(final ChessPiece chessPiece) {
        return this.moveCount == 0
                && chessPiece.moveCount == 0
                && chessPiece instanceof Rook;
    }

    private boolean isLeftThenRook(final ChessPiece chessPiece) {
        return this.position.column().isLeftThen(chessPiece.position.column());
    }
}
