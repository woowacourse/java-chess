package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.Map;

public class Knight extends Piece {

    private static final String BLACK_KNIGHT_UNICODE = "\u265E";
    private static final String WHITE_KNIGHT_UNICODE = "\u2658";

    private Knight(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new Knight(position, player);
    }

    @Override
    protected boolean checkMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        int fileDifference = position.getFileDifference(target);
        int rankDifference = position.getRankDifference(target);

        return fileDifference + rankDifference == 3 && fileDifference != 0 && rankDifference != 0;
    }

    @Override
    protected PieceState makePieceState() {
        return this;
    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_KNIGHT_UNICODE;
        }
        return WHITE_KNIGHT_UNICODE;
    }
}
