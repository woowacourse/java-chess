package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.Direction;

import java.util.Arrays;

public class Pawn extends Piece {
    private static PieceName pieceName = PieceName.valueOf("PAWN");

    public Pawn(Player player) {
        super(player, pieceName);
        directions.addAll(Arrays.asList(Direction.TOP,
                Direction.DIAGONAL_TOP_LEFT, Direction.DIAGONAL_TOP_RIGHT));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return true;
    }
}
