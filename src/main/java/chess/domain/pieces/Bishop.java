package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.direction.Direction;
import java.util.List;

public class Bishop extends Piece {

    private static final int ROW = 1;
    private static final int COLUMN = 0;

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position source, final Position destination) {
        int absSubRow = Math.abs(destination.calculateDistanceOfRow(source));
        int absSubCol = Math.abs(destination.calculateDistanceOfCol(source));

        if (!(absSubCol == absSubRow)) {
            throw new IllegalArgumentException("Bishop의 이동 범위가 올바르지 않습니다.");
        }
    }
}
