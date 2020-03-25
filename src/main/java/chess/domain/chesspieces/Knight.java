package chess.domain.chesspieces;

import chess.domain.moverules.MoveRule;

import java.util.Arrays;

public class Knight extends ChessPiece {
    public Knight(String name) {
        super(name);
        moveRules.addAll(Arrays.asList(MoveRule.values()));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return false;
    }
}
