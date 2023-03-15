package chess.view;

import chess.domain.Piece;
import chess.domain.Position;

import java.util.Map;

public class OutputView {
    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printChessBoard(final Map<Position, Piece> board) {
        final String chessBoard = BoardViewForm.createChessBoard(board);
        System.out.println(chessBoard);
    }
}
