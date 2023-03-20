package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class Rook extends Piece {

    private static final int ROW = 1;
    private static final int COLUMN = 0;

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position source, final Position destination) {
        validateMove(source, destination);
    }

    private void validateMove(final Position source, final Position destination) {
        int subRow = destination.calculateDistanceOfRow(source);
        int subCol = destination.calculateDistanceOfCol(source);

        if (subRow != 0 && subCol != 0) {
            throw new IllegalArgumentException("올바르지 않은 위치로 이동할 수 없습니다.");
        }
    }
}
