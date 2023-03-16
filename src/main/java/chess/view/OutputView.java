package chess.view;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {
    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.print(
                "> 체스 게임을 시작합니다." + System.lineSeparator() +
                        "> 게임 시작 : start" + System.lineSeparator() +
                        "> 게임 종료 : end" + System.lineSeparator() +
                        "> 게임 이동 : move source위치 target위치 - 예. move b2 b3" + System.lineSeparator()
        );
    }

    public static void printChessBoard(final Map<Position, Piece> board) {
        final String chessBoard = BoardViewForm.createChessBoard(board);
        System.out.println(chessBoard + System.lineSeparator());
    }
}
