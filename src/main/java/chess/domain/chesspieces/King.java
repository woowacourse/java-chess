package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.direction.Direction;

public class King extends Piece {
    private static final String KING_NAME = "KING";

    private static PieceInfo pieceInfo = PieceInfo.valueOf(KING_NAME);

    public King(Player player) {
        super(player, pieceInfo);
        directions.addAll(Direction.everyDirection());
    }
}
