package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;

public class MovedPawn extends NotMovedPawn {

    protected MovedPawn(Position position, Player player) {
        super(position, player);
    }

    public static MovedPawn of(Position position, Player player) {
        return new MovedPawn(position, player);
    }

    @Override
    protected boolean checkMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        int fileDifference = position.getFileDifference(target);
        int rankDifference = position.getRankDifference(target);
        PieceDto targetPiece = boardDto.get(target);

        if (checkPawnDirection(rankDifference)) {
            if (Objects.isNull(targetPiece)) {
                return Math.abs(rankDifference) == 1 && fileDifference == 0;
            }
            return Math.abs(rankDifference) == 1 && Math.abs(fileDifference) == 1;
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
        return this;
    }
}
