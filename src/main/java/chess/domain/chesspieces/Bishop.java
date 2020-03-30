package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.Arrays;
import java.util.Objects;

public class Bishop extends Piece{
    private static final int MOVABLE_ROW_SIZE = Row.values().length;
    private static final int MOVABLE_COLUMN_SIZE = Column.values().length;
    private static final String BISHOP_NAME = "BISHOP";

    private static PieceInfo pieceInfo = PieceInfo.valueOf(BISHOP_NAME);

    public Bishop(Player player) {
        super(player, pieceInfo);
        directions.addAll(Arrays.asList(Direction.DIAGONAL_DOWN_LEFT, Direction.DIAGONAL_DOWN_RIGHT,
                Direction.DIAGONAL_TOP_LEFT, Direction.DIAGONAL_TOP_RIGHT));
    }

    @Override
    public boolean validateTileSize(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        int rowDiff = Row.getDiff(from.getRow(), to.getRow());
        int columnDiff = Column.getDiff(from.getColumn(), to.getColumn());
        return Math.abs(rowDiff) <= MOVABLE_ROW_SIZE && Math.abs(columnDiff) <= MOVABLE_COLUMN_SIZE;
    }
}

