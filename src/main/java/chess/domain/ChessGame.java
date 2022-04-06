package chess.domain;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.position.Position;
import chess.result.EndResult;
import chess.result.MoveResult;
import chess.result.StartResult;

public class ChessGame {

    private ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.gameStatus = GameStatus.READY;
    }

    public ChessGame(final ChessBoard chessBoard, final GameStatus gameStatus) {
        this.chessBoard = chessBoard;
        this.gameStatus = gameStatus;
    }

    public MoveResult move(final Position from, final Position to) {
        gameStatus.checkPlaying();

        final MoveResult result = chessBoard.move(from, to);
        if (chessBoard.isKingDie()) {
            gameStatus = GameStatus.KING_DIE;
            result.changeStatusToKingDie();
        }
        return result;
    }

    public Score calculateScore() {
        gameStatus.checkPlaying();
        return chessBoard.calculateScore();
    }

    public StartResult start() {
        gameStatus.checkReady();
        if (gameStatus.isEnd()) {
            chessBoard = ChessBoardFactory.createChessBoard();
        }
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
