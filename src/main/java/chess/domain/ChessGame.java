package chess.domain;

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

    public void move(final Position from, final Position to) {
        gameStatus.checkPlaying();

        chessBoard.move(from, to);
        if (chessBoard.isKingDie()) {
            gameStatus = GameStatus.KING_DIE;
        }
    }

    public Score calculateScore() {
        gameStatus.checkPlaying();
        return chessBoard.calculateScore();
    }

    public Map<Position, ChessPiece> findAllPiece() {
        gameStatus.checkPlaying();
        return chessBoard.findAllPiece();
    }

    public void start() {
        gameStatus.checkReady();
        gameStatus = GameStatus.PLAYING;
    }

    public void end() {
        gameStatus = GameStatus.END;
    }

    public boolean isFinish() {
        return gameStatus.equals(GameStatus.KING_DIE);
    }

    public boolean canPlay() {
        return !gameStatus.isEnd();
    }
}
