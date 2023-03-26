package chess.game;

import chess.board.ChessBoard;
import chess.board.Position;
import chess.piece.Team;

public class ChessGame {

    private final ChessBoard chessBoard;
    private boolean isProcessing;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.isProcessing = false;
    }

    public void movePiece(final Position from, final Position to) {
        chessBoard.movePiece(from, to);
    }

    public void receiveCommand(final Command command) {
        if (command == Command.START) {
            this.isProcessing = true;
        }
        if (command == Command.END) {
            this.isProcessing = false;
        }
    }

    public boolean isProcessing() {
        if (chessBoard.isGameOver(Team.WHITE) || chessBoard.isGameOver(Team.BLACK)) {
            isProcessing = false;
        }
        return isProcessing;
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

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
