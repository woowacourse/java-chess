package chess.domain.chesspiece.concrete;

import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.PieceInfo;
import chess.domain.direction.Direction;
import chess.domain.game.Player;

public class King extends Piece {
    private static final String KING_NAME = "KING";

    public King(Player player) {
        super(player, PieceInfo.valueOf(KING_NAME));
        directions.addAll(Direction.everyDirection());
    }

    @Override
    public boolean needValidateObstacle() {
        return true;
    }
}
