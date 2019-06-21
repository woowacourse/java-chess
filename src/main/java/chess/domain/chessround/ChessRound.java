package chess.domain.chessround;

import chess.domain.chesspoint.ChessPoint;

public class ChessRound {
    private final ChessPlayer whitePlayer;
    private final ChessPlayer blackPlayer;
    private ChessPlayer currentPlayer;

    private ChessRound(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentPlayer = whitePlayer;
    }

    public static ChessRound of(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
        return new ChessRound(whitePlayer, blackPlayer);
    }

    public void move(ChessPoint source, ChessPoint target) {
        currentPlayer.assertExistenceOn(source);
        currentPlayer.assertEmptyOn(target);

        moveBy(source, target);

        currentPlayer = getOpponentPlayer();
    }

    private void moveBy(ChessPoint source, ChessPoint target) {
        ChessPlayer opponentPlayer = getOpponentPlayer();
        currentPlayer.move(source, target, opponentPlayer);
        if (opponentPlayer.contains(target)) {
            opponentPlayer.delete(target);
        }
    }

    private ChessPlayer getOpponentPlayer() {
        return currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }

    public ChessPlayer getWhitePlayer() {
        return whitePlayer;
    }

    public ChessPlayer getBlackPlayer() {
        return blackPlayer;
    }

    public ChessPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public double getWhitePlayerScore() {
        return whitePlayer.calculateScore();
    }

    public double getBlackPlayerScore() {
        return blackPlayer.calculateScore();
    }
}
