package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.Direction;
import chess.domain.position.Position;

import java.util.Arrays;

// 대각선
public class Bishop extends Piece {
    private static PieceName pieceName = PieceName.valueOf("BISHOP");

    public Bishop(Player player) {
        super(player, pieceName);
        directions.addAll(Arrays.asList(Direction.DIAGONAL_DOWN_LEFT, Direction.DIAGONAL_DOWN_RIGHT,
                Direction.DIAGONAL_TOP_LEFT, Direction.DIAGONAL_TOP_RIGHT));
    }

    @Override
    public boolean validateMovableTileSize(Position from, Position to) {
        return true;
    }
}

