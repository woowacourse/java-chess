package chess.domain.chesspiece;

import chess.domain.direction.Direction;
import chess.domain.game.Player;

public class King extends Piece {
    private static final String KING_NAME = "KING";

    private static PieceInfo pieceInfo = PieceInfo.valueOf(KING_NAME);

    public King(Player player) {
        super(player, pieceInfo);
        directions.addAll(Direction.everyDirection());
    }

    @Override
    public boolean needValidateObstacle() {
        return true;
    }
}
