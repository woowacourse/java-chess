package chess.domain;

import chess.controller.result.EndResult;
import chess.controller.result.MoveResult;
import chess.controller.result.StartResult;
import chess.domain.chessboard.ChessBoard;
import chess.domain.position.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.gameStatus = GameStatus.READY;
    }

    public MoveResult move(final Position from, final Position to) {
        gameStatus.checkPlaying();

        final MoveResult result = chessBoard.move(from, to);
        if (chessBoard.isKingDie()) {
            gameStatus = GameStatus.KING_DIE;
        }
        return result;
    }

    public Score calculateScore() {
        gameStatus.checkPlaying();
        return chessBoard.calculateScore();
    }

    public StartResult start() {
        gameStatus.checkReady();
        gameStatus = GameStatus.PLAYING;
        return new StartResult(chessBoard.findAllPiece());
    }

    public EndResult end() {
        gameStatus = GameStatus.END;
        final Score score = new Score(chessBoard.findAllPiece());
        return new EndResult(score);
    }

    public boolean canPlay() {
        return !gameStatus.isEnd();
    }
}
