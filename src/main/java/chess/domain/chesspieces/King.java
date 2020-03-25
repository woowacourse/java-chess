package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.MoveRule;

import java.util.Arrays;

public class King extends Piece {
    private static final int MOVABLE_TILE_SIZE = 1;
    private static PieceName pieceName = PieceName.valueOf("KING");

    public King(Player player) {
        super(player, pieceName);
        moveRules.addAll(Arrays.asList(MoveRule.values()));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return Math.abs(rowDiff) <= MOVABLE_TILE_SIZE && Math.abs(columnDiff) <= MOVABLE_TILE_SIZE;
    }
}
