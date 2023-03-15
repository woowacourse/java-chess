package chess.domain.pieces;

import chess.domain.board.Position;

public class Bishop extends Piece {

    public Bishop(final Position position) {
        super(position);
    }

    @Override
    public void move(final String position) {
        validateMove(position);
        this.position = new Position(position);
    }

    private void validateMove(final String position) {
        int absSubRow = Math.abs(this.position.subRowFromArriveRow(position));
        int absSubCol = Math.abs(this.position.subColFromArriveCol(position));

        if (!(absSubCol == absSubRow)) {
            throw new IllegalArgumentException("Bishop의 이동 범위가 올바르지 않습니다.");
        }
    }
}
