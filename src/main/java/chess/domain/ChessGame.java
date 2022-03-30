package chess.domain;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Optional;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;
    private Color currentTurnColor;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.gameStatus = GameStatus.READY;
        this.currentTurnColor = Color.WHITE;
    }

    public void move(final Position from, final Position to) {
        if (!gameStatus.isPlaying()) {
            throw new IllegalArgumentException("게임이 이미 종료되었습니다.");
        }
        checkCurrentTurn(from);
        chessBoard.move(from, to);
        if (chessBoard.isKingDie()) {
            gameStatus = GameStatus.KING_DIE;
        }
        currentTurnColor = currentTurnColor.toOpposite();
    }

    private void checkCurrentTurn(final Position from) {
        final Optional<ChessPiece> possiblePiece = chessBoard.findPiece(from);
        if (possiblePiece.isEmpty()) {
            return;
        }
        final ChessPiece chessPiece = possiblePiece.get();
        if (!chessPiece.isSameColor(currentTurnColor)) {
            throw new IllegalArgumentException(currentTurnColor.name() + "의 차례입니다.");
        }
    }

    public Score calculateScore() {
        return chessBoard.calculateScore();
    }

    public Map<Position, ChessPiece> findAllPiece() {
        return chessBoard.findAllPiece();
    }

    public boolean isSameStatus(GameStatus gameStatus) {
        return this.gameStatus.equals(gameStatus);
    }

    public void start() {
        gameStatus = GameStatus.PLAYING;
    }

    public void end() {
        gameStatus = GameStatus.END;
    }
}
