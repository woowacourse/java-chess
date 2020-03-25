package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.Direction;

import java.util.Arrays;

public class Queen extends Piece {
    private static PieceName pieceName = PieceName.valueOf("QUEEN");

    public Queen(Player player) {
        super(player, pieceName);
        directions.addAll(Arrays.asList(Direction.values()));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return true;
    }
}
