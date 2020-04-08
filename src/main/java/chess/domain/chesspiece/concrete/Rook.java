package chess.domain.chesspiece.concrete;

import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.PieceInfo;
import chess.domain.direction.Direction;
import chess.domain.game.Player;

public class Rook extends Piece {
    private static final String ROOK_NAME = "ROOK";

    public Rook(Player player) {
        super(player, PieceInfo.valueOf(ROOK_NAME));
        directions.addAll(Direction.diagonalDirection());
    }

    @Override
    public boolean needValidateObstacle() {
        return true;
    }
}
