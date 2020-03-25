package chess.domain.chesspieces;

import chess.domain.moverules.MoveRule;
import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.Arrays;
import java.util.Objects;

public class King extends ChessPiece {
    private static final int MOVABLE_TILE_SIZE = 1;
    public King(String name) {
        super(name);
        moveRules.addAll(Arrays.asList(MoveRule.values()));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return Math.abs(rowDiff) <= MOVABLE_TILE_SIZE && Math.abs(columnDiff) <= MOVABLE_TILE_SIZE;
    }
}
