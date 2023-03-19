package chess.game;

import chess.board.ChessBoard;
import chess.board.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private boolean processStatus;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.processStatus = false;
    }

    public void movePiece(final Position from, final Position to) {
        chessBoard.movePiece(from, to);
    }

    public void receiveCommand(final Command command) {
        if (command == Command.START) {
            this.processStatus = true;
        }
        if (command == Command.END) {
            this.processStatus = false;
        }
    }

    public boolean isEnd() {
        return !processStatus;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

}
