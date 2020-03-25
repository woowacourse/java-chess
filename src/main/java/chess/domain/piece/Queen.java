package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.Map;

public class Queen extends Piece {

    private static final String BLACK_QUEEN_UNICODE = "\u265B";
    private static final String WHITE_PAWN_UNICODE = "\u2655";

    private Queen(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new Queen(position, player);
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
            return BLACK_QUEEN_UNICODE;
        }
        return WHITE_PAWN_UNICODE;
    }
}
