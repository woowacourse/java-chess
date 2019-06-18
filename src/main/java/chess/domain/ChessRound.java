package chess.domain;

import chess.domain.chesspoint.ChessPoint;

public class ChessRound {
    private final ChessPlayer whitePlayer;
    private final ChessPlayer blackPlayer;
    private boolean isWhiteTurn = true;

    public ChessRound(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public void move(ChessPoint source, ChessPoint target) {
        if (isWhiteTurn) {
            if (!whitePlayer.contains(source)) {
                throw new IllegalArgumentException("해당 플레이어에 말이 없습니다.");
            }

            // 타겟이 아군인 경우
            if (whitePlayer.contains(target)) {
                throw new IllegalArgumentException("타겟에 아군이 있습니다.");
            }

            // 타겟이 적군이 경우
            whitePlayer.move(source, target);
            if (blackPlayer.contains(target)) {
                blackPlayer.delete(target);
            }
            isWhiteTurn = false;
        } else {
            if (!blackPlayer.contains(source)) {
                throw new IllegalArgumentException("해당 플레이어에 말이 없습니다.");
            }

            // 타겟이 아군인 경우
            if (blackPlayer.contains(target)) {
                throw new IllegalArgumentException("타겟에 아군이 있습니다.");
            }

            // 타겟이 적군이 경우
            blackPlayer.move(source, target);
            if (whitePlayer.contains(target)) {
                whitePlayer.delete(target);
            }
            isWhiteTurn = true;
        }
    }
}
