package chess.domain.chesspieces;

import chess.domain.direction.Direction;
import chess.domain.game.Player;

public class Bishop extends Piece {
    private static final String BISHOP_NAME = "BISHOP";

    private static PieceInfo pieceInfo = PieceInfo.valueOf(BISHOP_NAME);

    public Bishop(Player player) {
        super(player, pieceInfo);
        directions.addAll(Direction.diagonalDirection());
    }
}

