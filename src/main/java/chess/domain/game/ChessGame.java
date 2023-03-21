package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;

import static chess.domain.game.GameStatus.IDLE;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.gameStatus = IDLE;
    }

    public void receiveCommand(final Command command) {
        this.gameStatus = command.getStatus();
    }

    public void movePiece(final Position from, final Position to) {
        chessBoard.movePiece(from, to);
    }

    public boolean isEnd() {
        return gameStatus == IDLE;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
