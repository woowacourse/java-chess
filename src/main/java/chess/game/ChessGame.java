package chess.game;

import chess.board.ChessBoard;
import chess.board.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private boolean isProcessing;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
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
        return isProcessing;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

}
