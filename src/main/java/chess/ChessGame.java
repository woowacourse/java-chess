package chess;

import chess.domain.ChessBoard;
import chess.domain.Turn;
import java.util.List;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Turn turn;

    public ChessGame(ChessBoard chessBoard, Turn turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    public void start() {
        chessBoard.initBoard();
    }

    public void run(List<String> movement) {
        chessBoard.move(movement.get(1), movement.get(2));
        turn.changeTurn();
        // 턴 검사
        // 유효한 위치인지 확인 (a~h, 1~8)
    }

    public void end() {

    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
