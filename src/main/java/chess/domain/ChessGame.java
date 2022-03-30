package chess.domain;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private final GameStatus gameStatus;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.gameStatus = new GameStatus();
    }

    public void move(final Position from, final Position to) {
        gameStatus.checkCanMove(chessBoard.findPiece(from));

        chessBoard.move(from, to);
        if (chessBoard.isKingDie()) {
            gameStatus.kingDie();
        }
        gameStatus.changeTurn();
    }

    public Score calculateScore() {
        gameStatus.checkCanCalculateScore();
        return chessBoard.calculateScore();
    }

    public Map<Position, ChessPiece> findAllPiece() {
        return chessBoard.findAllPiece();
    }

    public void start() {
        gameStatus.start();
    }

    public void end() {
        gameStatus.end();
    }

    public boolean isFinish() {
        return gameStatus.isKingDie();
    }

    public boolean canPlay() {
        return gameStatus.canPlay();
    }
}
