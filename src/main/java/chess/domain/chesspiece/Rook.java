package chess.domain.chesspiece;

import chess.domain.direction.Direction;
import chess.domain.game.Player;

public class Rook extends Piece {
    private static final String ROOK_NAME = "ROOK";

    private static PieceInfo pieceInfo = PieceInfo.valueOf(ROOK_NAME);

    public Rook(Player player) {
        super(player, pieceInfo);
        directions.addAll(Direction.diagonalDirection());
    }

    @Override
    public boolean needValidateObstacle() {
        return true;
    }
}
