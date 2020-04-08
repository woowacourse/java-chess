package chess.domain.chesspiece.concrete;

import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.PieceInfo;
import chess.domain.direction.Direction;
import chess.domain.game.Player;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "QUEEN";

    public Queen(Player player) {
        super(player, PieceInfo.valueOf(QUEEN_NAME));
        directions.addAll(Direction.everyDirection());
    }

    @Override
    public boolean needValidateObstacle() {
        return true;
    }
}
