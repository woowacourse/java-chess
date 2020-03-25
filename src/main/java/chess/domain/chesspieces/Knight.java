package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.MoveRule;

import java.util.Arrays;

public class Knight extends Piece {
    private static PieceName pieceName = PieceName.valueOf("KNIGHT");

    public Knight(Player player) {
        super(player, pieceName);
        moveRules.addAll(Arrays.asList(MoveRule.values()));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return false;
    }
}
