package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.MoveRule;

import java.util.Arrays;

public class Rook extends Piece {
    private static PieceName pieceName = PieceName.valueOf("ROOK");

    public Rook(Player player) {
        super(player, pieceName);
        moveRules.addAll(Arrays.asList(MoveRule.TOP, MoveRule.DOWN,
                MoveRule.LEFT, MoveRule.RIGHT));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return true;
    }
}
