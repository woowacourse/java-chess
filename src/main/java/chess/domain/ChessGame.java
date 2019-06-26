package chess.domain;

import java.util.List;

import chess.exception.GameOverException;
import chess.exception.UnmovableException;
import chess.domain.piece.Piece;

public class ChessGame {
    private ChessBoard chessBoard;
    private Player currentPlayer;

    public ChessGame(ChessBoard chessBoard) {
        currentPlayer = Player.WHITE;
        this.chessBoard = chessBoard;
    }

    public ChessGame(Player player, ChessBoard chessBoard) {
        currentPlayer = player;
        this.chessBoard = chessBoard;
    }

    public void move(Position start, Position end) {
        Piece startPiece = chessBoard.findStartPiece(currentPlayer, start);
        Piece endPiece = chessBoard.findEndPiece(end);
        Path path = getPath(end, startPiece, endPiece);

        if (!chessBoard.isMovable(path)) {
            throw new UnmovableException();
        }
        if (endPiece.isMine(currentPlayer.changePlayer())) {
            chessBoard.remove(endPiece);
            checkGameOver(endPiece);
        }
        startPiece.changePosition(end);
        currentPlayer = currentPlayer.changePlayer();
    }

    private void checkGameOver(Piece endPiece) {
        if (endPiece.isKing()) {
            throw new GameOverException("게임종료! " + currentPlayer.name() + " 승리");
        }
    }

    private Path getPath(Position end, Piece startPiece, Piece endPiece) {
        if (endPiece.isEmpty()) {
            return startPiece.getMovablePath(end);
        }
        if (!endPiece.isMine(currentPlayer)) {
            return startPiece.getAttackablePath(end);
        }
        throw new UnmovableException();
    }

    public Result findWinner() {
        Score blackScore = getPlayerScore(Player.BLACK);
        Score whiteScore = getPlayerScore(Player.WHITE);
        if (blackScore.equals(whiteScore)) {
            return Result.DRAW;
        }
        if (blackScore.isHigher(whiteScore)) {
            return Result.BLACK_WIN;
        }
        return Result.WHITE_WIN;
    }

    public Score getPlayerScore(Player player) {
        return chessBoard.getPlayerScore(player);
    }

    public List<Piece> getPieces() {
        return chessBoard.getPieces();
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
