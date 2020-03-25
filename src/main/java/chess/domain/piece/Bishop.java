package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.Map;

public class Bishop extends Piece {

    private static final String BLACK_BISHOP_UNICODE = "\u265D";
    private static final String WHITE_BISHOP_UNICODE = "\u2657";

    private Bishop(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new Bishop(position, player);
    }

    @Override
    protected boolean checkMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        return false;
    }

    @Override
    protected PieceState makePieceState() {
        return null;
    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_BISHOP_UNICODE;
        }
        return WHITE_BISHOP_UNICODE;
    }
}
