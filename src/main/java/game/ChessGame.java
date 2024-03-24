package game;

import domain.ChessBoard;
import domain.piece.Piece;
import domain.square.Square;

import java.util.Map;

public class ChessGame {

    private ChessBoard chessBoard;
    private ChessGameStatus chessGameStatus;

    public ChessGame() {
        this.chessGameStatus = ChessGameStatus.INIT;
    }

    public void start() {
        if (chessGameStatus == ChessGameStatus.INIT) {
            chessBoard = ChessBoard.create();
            chessGameStatus = ChessGameStatus.RUNNING;
            return;
        }
        throw new IllegalArgumentException("초기 상태에서만 게임을 시작할 수 있습니다.");
    }

    public void move(final Square source, final Square target) {
        if (chessGameStatus == ChessGameStatus.RUNNING) {
            chessBoard.move(source, target);
            return;
        }
        throw new IllegalArgumentException("게임이 진행 중일 때만 움직일 수 있습니다.");
    }

    public void end() {
        chessGameStatus = ChessGameStatus.END;
    }

    public boolean isEnd() {
        return chessGameStatus == ChessGameStatus.END;
    }

    public Map<Square, Piece> getPieceSquares() {
        return chessBoard.getPieceSquares();
    }
}
