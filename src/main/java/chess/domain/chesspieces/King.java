package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.Arrays;
import java.util.Objects;

public class King extends Piece {
    private static final int MOVABLE_TILE_SIZE = 1;
    private static final String KING_NAME = "KING";

    private static PieceInfo pieceInfo = PieceInfo.valueOf(KING_NAME);

    public King(Player player) {
        super(player, pieceInfo);
        directions.addAll(Arrays.asList(Direction.values()));
    }

    @Override
    public boolean validateTileSize(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        int rowDiff = Row.getDiff(from.getRow(), to.getRow());
        int columnDiff = Column.getDiff(from.getColumn(), to.getColumn());
        return Math.abs(rowDiff) <= MOVABLE_TILE_SIZE && Math.abs(columnDiff) <= MOVABLE_TILE_SIZE;
    }
}
