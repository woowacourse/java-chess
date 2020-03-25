package chess.domain.chesspieces;

import chess.domain.moverules.MoveRule;

import java.util.Arrays;

public class Bishop extends ChessPiece {
    public Bishop(String name) {
        super(name);
        moveRules.addAll(Arrays.asList(MoveRule.DIAGONAL_DOWN_LEFT, MoveRule.DIAGONAL_DOWN_RIGHT,
                MoveRule.DIAGONAL_TOP_LEFT, MoveRule.DIAGONAL_TOP_RIGHT));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return false;
    }
}
