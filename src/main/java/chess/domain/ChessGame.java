package chess.domain;

import chess.controller.result.Result;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.gameStatus = GameStatus.READY;
    }

    public Result move(final Position from, final Position to) {
        gameStatus.checkPlaying();

        final Result moveResult = chessBoard.move(from, to);
        if (chessBoard.isKingDie()) {
            gameStatus = GameStatus.KING_DIE;
        }
        return moveResult;
    }

    public Score calculateScore() {
        gameStatus.checkPlaying();
        return chessBoard.calculateScore();
    }

    public Map<Position, ChessPiece> start() {
        gameStatus.checkReady();
        gameStatus = GameStatus.PLAYING;
        return chessBoard.findAllPiece();
    }

    public Score end() {
        gameStatus = GameStatus.END;
        return chessBoard.calculateScore();
    }

    public boolean canPlay() {
        return !gameStatus.isEnd();
    }
}
