package chess.game;

import chess.board.ChessBoard;
import chess.board.Position;
import chess.piece.Team;

public class ChessGame {

    private final ChessBoard chessBoard;
    private boolean isProcessing;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.isProcessing = true;
    }

    public void movePiece(final Position from, final Position to) {
        chessBoard.movePiece(from, to);
    }

    public boolean isProcessing() {
        if (chessBoard.isKingKilled(Team.WHITE) || chessBoard.isKingKilled(Team.BLACK)) {
            isProcessing = false;
        }
        return isProcessing;
    }

    public double calculateScore(final Team team) {
        return chessBoard.calculateScore(team);
    }

    public Team findWinner() {
        if (chessBoard.calculateScore(Team.WHITE) > chessBoard.calculateScore(Team.BLACK)) {
            return Team.WHITE;
        }
        if (chessBoard.calculateScore(Team.WHITE) < chessBoard.calculateScore(Team.BLACK)) {
            return Team.BLACK;
        }
        return null;
    }

    public void end() {
        isProcessing = false;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
