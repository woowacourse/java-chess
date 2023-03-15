package chess.domain.pieces;

import chess.domain.board.Position;

public class Rook extends Piece {

    public Rook(final Position position) {
        super(position);
    }

    @Override
    public void move(final String position) {
        validateMove(position);
        this.position = new Position(position);
    }

    private void validateMove(final String position) {
        int substitutionOfRow = this.position.subRowFromArriveRow(position);
        int substitutionOfCol = this.position.subColFromArriveCol(position);

        if (substitutionOfRow != 0 && substitutionOfCol != 0) {
            throw new IllegalArgumentException("올바르지 않은 위치로 이동할 수 없습니다.");
        }
    }
}
