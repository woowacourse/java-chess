package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.Direction;
import chess.domain.position.Position;

import java.util.Arrays;

public class Rook extends Piece {
    private static PieceName pieceName = PieceName.valueOf("ROOK");

    public Rook(Player player) {
        super(player, pieceName);
        directions.addAll(Arrays.asList(Direction.TOP, Direction.DOWN,
                Direction.LEFT, Direction.RIGHT));
    }

    @Override
    public boolean validateMovableTileSize(Position from, Position to) {
        return true;
    }
}
