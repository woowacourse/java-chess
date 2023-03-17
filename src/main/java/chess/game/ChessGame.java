package chess.game;

import chess.board.ChessBoard;
import chess.board.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private boolean endStatus;

    public ChessGame() {
        this.chessBoard = ChessBoard.createBoard();
        this.endStatus = false;
    }

    public void movePiece(final Position from, final Position to) {
        chessBoard.movePiece(from, to);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public boolean isEnd() {
        return endStatus;
    }

    public void receiveCommand(final Command command) {
        if (command == Command.START) {
            this.endStatus = false;
        }
        if (command == Command.END) {
            this.endStatus = true;
        }
    }
}
