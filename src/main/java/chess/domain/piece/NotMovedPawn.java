package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;

public class NotMovedPawn extends Piece {

    private static final String BLACK_PAWN_UNICODE = "\u265F";
    private static final String WHITE_PAWN_UNICODE = "\u2659";

    protected NotMovedPawn(Position position, Player player) {
        super(position, player);
    }

    public static NotMovedPawn of(Position position, Player player) {
        return new NotMovedPawn(position, player);
    }

    @Override
    protected boolean checkMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        int fileDifference = position.getFileDifference(target);
        int rankDifference = position.getRankDifference(target);
        PieceDto targetPiece = boardDto.get(target);

        checkPawnDirection(rankDifference);

        if (Objects.isNull(targetPiece) && Math.abs(rankDifference) == 1) {
            return true;
        }

        if (Objects.isNull(targetPiece) && Math.abs(rankDifference) == 2) {
            // 플레이어에 따라 Rank에 -1, +1위치에 해당 말이 있는지 확인
        }


        if (fileDifference == 0 && rankDifference <= 2) {
            return false;

        }
        return false;
    }

    private boolean checkPawnDirection(int rankDifference) {
        if (player == Player.WHITE) {
            return rankDifference > 0;
        }
        return rankDifference < 0;

    }

    @Override
    protected PieceState makePieceState() {
        return null;
    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_PAWN_UNICODE;
        }
        return WHITE_PAWN_UNICODE;
    }
}
