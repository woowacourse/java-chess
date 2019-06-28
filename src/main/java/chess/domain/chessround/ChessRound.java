package chess.domain.chessround;

import chess.domain.chesspoint.ChessPoint;

public class ChessRound {
    private final ChessPlayer whitePlayer;
    private final ChessPlayer blackPlayer;
    private ChessPlayer currentPlayer;

    private ChessRound(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
        this(whitePlayer, blackPlayer, true);
    }

    private ChessRound(ChessPlayer whitePlayer, ChessPlayer blackPlayer, boolean isWhiteTurn) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentPlayer = isWhiteTurn ? whitePlayer : blackPlayer;
    }

    public static ChessRound of(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
        return new ChessRound(whitePlayer, blackPlayer);
    }

    public static ChessRound of(ChessPlayer whitePlayer, ChessPlayer blackPlayer, boolean isWhiteTurn) {
        return new ChessRound(whitePlayer, blackPlayer, isWhiteTurn);
    }

    public void move(ChessPoint source, ChessPoint target) {
        currentPlayer.assertExistenceOn(source);
        currentPlayer.assertEmptyOn(target);

        ChessPlayer opponentPlayer = getOpponentPlayer();
        currentPlayer.move(source, target, opponentPlayer);

        if (isOpponentPieceOn(target, opponentPlayer)) {
            opponentPlayer.delete(target);
        }
        currentPlayer = getOpponentPlayer();
    }

    private boolean isOpponentPieceOn(ChessPoint target, ChessPlayer opponentPlayer) {
        return opponentPlayer.contains(target);
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

    public ChessPlayer getCurrentOpponentPlayer() {
        return getOpponentPlayer();
    }

    public double getWhitePlayerScore() {
        return whitePlayer.calculateScore();
    }

    public double getBlackPlayerScore() {
        return blackPlayer.calculateScore();
    }

    public boolean isWhiteTurn() {
        return currentPlayer == whitePlayer;
    }

    public boolean isGameFinished() {
        return !currentPlayer.isKingAlive();
    }
}
