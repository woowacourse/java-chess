package chess.domain.chesspieces;

import chess.domain.moverules.MoveRule;

import java.util.Arrays;

public class Rook extends ChessPiece{
    public Rook(String name) {
        super(name);
        moveRules.addAll(Arrays.asList(MoveRule.TOP, MoveRule.DOWN,
                MoveRule.LEFT, MoveRule.RIGHT));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return false;
    }
}
