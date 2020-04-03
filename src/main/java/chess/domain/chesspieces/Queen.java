package chess.domain.chesspieces;

import chess.domain.direction.Direction;
import chess.domain.game.Player;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "QUEEN";

    private static PieceInfo pieceInfo = PieceInfo.valueOf(QUEEN_NAME);

    public Queen(Player player) {
        super(player, pieceInfo);
        directions.addAll(Direction.everyDirection());
    }
}
