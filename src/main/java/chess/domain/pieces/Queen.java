package chess.domain.pieces;

import chess.domain.board.Position;

public class Queen extends Piece {

    public Queen(final Position position) {
        super(position);
    }

    @Override
    public void move(final String position) {
        validateMove(position);
        this.position = new Position(position);
    }

    private void validateMove(final String position) {
        int subRow = this.position.subRowFromArriveRow(position);
        int subCol = this.position.subColFromArriveCol(position);

        if (!validateMoveLikeBishop(subRow, subCol) && !validateMoveLikeRook(subRow, subCol)) {
            throw new IllegalArgumentException("Queen의 이동 범위가 올바르지 않습니다.");
        }
    }

    private boolean validateMoveLikeBishop(final int subRow, final int subCol) {
        return Math.abs(subCol) == Math.abs(subRow);
    }

    private boolean validateMoveLikeRook(final int subRow, final int subCol) {
        return subRow == 0 || subCol == 0;
    }
}
