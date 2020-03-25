package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.MoveRule;

import java.util.Arrays;

public class Bishop extends Piece {
    private static PieceName pieceName = PieceName.valueOf("BISHOP");

    public Bishop(Player player) {
        super(player, pieceName);
        moveRules.addAll(Arrays.asList(MoveRule.DIAGONAL_DOWN_LEFT, MoveRule.DIAGONAL_DOWN_RIGHT,
                MoveRule.DIAGONAL_TOP_LEFT, MoveRule.DIAGONAL_TOP_RIGHT));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return true;
    }
}
