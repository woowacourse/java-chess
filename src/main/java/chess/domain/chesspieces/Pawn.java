package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.MoveRule;

import java.util.Arrays;

public class Pawn extends Piece {
    private static PieceName pieceName = PieceName.valueOf("PAWN");

    public Pawn(Player player) {
        super(player, pieceName);
        moveRules.addAll(Arrays.asList(MoveRule.TOP,
                MoveRule.DIAGONAL_TOP_LEFT, MoveRule.DIAGONAL_TOP_RIGHT));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return false;
    }
}
