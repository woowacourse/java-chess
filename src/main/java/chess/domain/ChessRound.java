package chess.domain;

import chess.domain.chesspoint.ChessPoint;

public class ChessRound {
    private final ChessPlayer whitePlayer;
    private final ChessPlayer blackPlayer;
    private ChessPlayer currentPlayer;

    public ChessRound(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentPlayer = whitePlayer;
    }

    public void move(ChessPoint source, ChessPoint target) {
        ChessPlayer opponentPlayer = oppositePlayer();

        if (!currentPlayer.contains(source)) {
            throw new IllegalArgumentException("해당 플레이어에 말이 없습니다.");
        }

        // 타겟이 아군인 경우
        if (currentPlayer.contains(target)) {
            throw new IllegalArgumentException("타겟에 아군이 있습니다.");
        }

        // 타겟이 적군이 경우
        currentPlayer.move(source, target, opponentPlayer);
        if (opponentPlayer.contains(target)) {
            opponentPlayer.delete(target);
        }
        currentPlayer = opponentPlayer;
    }

    private ChessPlayer oppositePlayer() {
        return currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }
}
