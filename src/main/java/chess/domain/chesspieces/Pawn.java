package chess.domain.chesspieces;

import chess.domain.moverules.MoveRule;

import java.util.Arrays;

public class Pawn extends ChessPiece {
    public Pawn(String name) {
        super(name);
        moveRules.addAll(Arrays.asList(MoveRule.TOP,
                MoveRule.DIAGONAL_TOP_LEFT, MoveRule.DIAGONAL_TOP_RIGHT));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return false;
    }
}
