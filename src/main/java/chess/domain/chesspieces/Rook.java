package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.direction.Direction;

public class Rook extends Piece {
    private static final String ROOK_NAME = "ROOK";

    private static PieceInfo pieceInfo = PieceInfo.valueOf(ROOK_NAME);

    public Rook(Player player) {
        super(player, pieceInfo);
        directions.addAll(Direction.diagonalDirection());
    }
}
