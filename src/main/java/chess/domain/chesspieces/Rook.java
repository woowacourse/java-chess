package chess.domain.chesspieces;

import chess.domain.direction.Direction;
import chess.domain.game.Player;

public class Rook extends Piece {
    private static final String ROOK_NAME = "ROOK";

    private static PieceInfo pieceInfo = PieceInfo.valueOf(ROOK_NAME);

    public Rook(Player player) {
        super(player, pieceInfo);
        directions.addAll(Direction.diagonalDirection());
    }
}
