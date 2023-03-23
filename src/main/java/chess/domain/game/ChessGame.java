package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import static chess.domain.game.GameStatus.IDLE;

public class ChessGame {

    private final ChessBoard chessBoard;
    private final Turn turn;
    private GameStatus gameStatus;

    public ChessGame(final ChessBoard chessBoard, final Turn turn, GameStatus status) {
        this.chessBoard = chessBoard;
        this.turn = turn;
        this.gameStatus = status;
    }

    public void receiveCommand(final Command command) {
        this.gameStatus = command.getStatus();
    }

    public void movePiece(final Position from, final Position to) {
        Piece currentPiece = chessBoard.get(from);
        if (!turn.isCurrent(currentPiece.getTeam())) {
            throw new IllegalArgumentException(turn.getCurrentTeam() + "의 차례입니다.");
        }
        chessBoard.movePiece(from, to);
        turn.next();
    }

    public boolean isEnd() {
        return gameStatus == IDLE;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
